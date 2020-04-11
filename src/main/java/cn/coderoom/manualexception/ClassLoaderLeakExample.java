package cn.coderoom.manualexception;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/** 
 * 
 * @class ClassLoaderLeakExample
 * @package cn.coderoom.manualexception
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/14 16:48 
*/ 
public class ClassLoaderLeakExample {

    static volatile boolean running = true;

    public static void main(String[] args) throws Exception {
        Thread thread = new LongRunningThread();
        try {
            thread.start();
            System.out.println("Running, press any key to stop.");
            System.in.read();
        } finally {
            running = false;
            thread.join();
        }
    }

    /**
     * 线程的实现只是循环调用
     * {@link #loadAndDiscard()}
     */
    static final class LongRunningThread extends Thread {
        @Override public void run() {
            while(running) {
                try {
                    loadAndDiscard();
                } catch (Throwable ex) {
                    ex.printStackTrace();
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    System.out.println("Caught InterruptedException, shutting down.");
                    running = false;
                }
            }
        }
    }

    /**
     * 这是一个简单的ClassLoader实现，只能加载一个类
     * 即LoadedInChildClassLoader类.这里需要解决一些麻烦
     * 必须确保每次得到一个新的类
     * (而非系统class loader提供的
     * 重用类).如果此子类所在JAR文件不在系统的classpath中,
     * 不需要这么麻烦.
     */
    static final class ChildOnlyClassLoader extends ClassLoader {
        ChildOnlyClassLoader() {
            super(ClassLoaderLeakExample.class.getClassLoader());
        }

        @Override protected Class<?> loadClass(String name, boolean resolve)
                throws ClassNotFoundException {
            if (!LoadedInChildClassLoader.class.getName().equals(name)) {
                return super.loadClass(name, resolve);
            }
            try {
                Path path = Paths.get(LoadedInChildClassLoader.class.getName()
                        + ".class");
                byte[] classBytes = Files.readAllBytes(path);
                Class<?> c = defineClass(name, classBytes, 0, classBytes.length);
                if (resolve) {
                    resolveClass(c);
                }
                return c;
            } catch (IOException ex) {
                throw new ClassNotFoundException("Could not load " + name, ex);
            }
        }
    }

    /**
     * Helper方法会创建一个新的ClassLoader, 加载一个类,
     * 然后丢弃对它们的所有引用.从理论上讲，应该不会影响GC
     * 因为没有引用可以逃脱该方法! 但实际上，
     * 结果会像筛子一样泄漏内存.
     */
    static void loadAndDiscard() throws Exception {
        ClassLoader childClassLoader = new ChildOnlyClassLoader();
        Class<?> childClass = Class.forName(
                LoadedInChildClassLoader.class.getName(), true, childClassLoader);
        childClass.newInstance();
        // 该方法返回时，将无法访问
        // childClassLoader或childClass的引用，
        // 但是这些对象仍会成为GC Root!
    }

    /**
     * 一个看起来人畜无害的类，没有做什么特别的事情.
     */
    public static final class LoadedInChildClassLoader {
        // 获取一些bytes.对于泄漏不是必需的，
        // 只是让效果出得更快一些.
        // 注意：这里开始真正泄露内存，这些bytes
        // 每次迭代都为这个final静态字段创建了!
        static final byte[] moreBytesToLeak = new byte[1024 * 1024 * 10];

        private static final ThreadLocal<LoadedInChildClassLoader> threadLocal
                = new ThreadLocal<>();

        public LoadedInChildClassLoader() {
            // 在ThreadLocal中存储对这个类的引用
            threadLocal.set(this);
        }
    }
    
}
