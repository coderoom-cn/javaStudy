package cn.coderoom.makebugs.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 模拟OOM内存溢出 <br/>
 * 需求：模拟了一个线程数为500的线程池，所有线程共享一个ThreadLocal变量，每一个线程执行的时候插入一个大的List集合。<br/>
 * 前置条件：设置JVM参数设置最大内存为256M(VM option : -Xmx256m )，以便模拟出OOM。<br/>
 * 监测工具：JDK工具jconsole
 * 预想结果：线程池的一个线程使用完ThreadLocal对象之后，再也不用，由于线程池中的线程不会退出，线程池中的线程的存在，同时ThreadLocal变量也会存在，占用内存！造成OOM溢出！
 * @class ThreadLocalOOMDemo
 * @package cn.coderoom.makebugs.thread
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/4/2 9:25 
*/ 
public class ThreadLocalOOMDemo {

    /**
     * 线程池大小
    */
    private static final int THREAD_LOOP_SIZE = 500;
    /**
     * 模拟数据量
    */
    private static final int MOCK_DIB_DATA_LOOP_SIZE = 10000;

    private static ThreadLocal<List<User>> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(THREAD_LOOP_SIZE);

        for (int i = 0; i < THREAD_LOOP_SIZE; i++) {
            executorService.execute(() -> {
                threadLocal.set(new ThreadLocalOOMDemo().addBigList());
                Thread t = Thread.currentThread();
                System.out.println(Thread.currentThread().getName());
                /**
                 * 是否释放该线程：改想成引用的数据声明周期结束的时候一定要释放。
                 * 或者ThreadLocal.set(null) <br/>
                 * 原因 ：ThreadLocalMap中Entry是实现了一个弱引用WeakReference，虽然Entry的key被包装成了一个弱引用会被垃圾回收机制给回收，
                 * 但是value在线程(Thread)不死亡时却可能存在一条强引用链（Thread—ThreadLocalMap—Entry—value）.
                 * 虽然ThreadLocal的作者想到了这点，也做了些优化，例如在get的时候当发现key是null的时候，会遍历一次整个Entry数组，remove掉key为null的entry，把value指向null，消除这条强引用链。源码方法为expungeStaleEntry
                 * 但是这个消除强引用链的动作是需要业务方在get的情况下触发的，可能业务方并不会get、也可能get是key不为空，并不会触发expungeStaleEntry类。
                 * ThreadLocal已经帮我们把key为null的Entry清理了，在ThreadLocal的get(),set(),remove()的时候都会清除线程ThreadLocalMap里所有key为null的value。
                */
                //threadLocal.remove(); //不取消注释的话就可能出现OOM
            });
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        //executorService.shutdown();
    }

    private List<User> addBigList() {
        List<User> params = new ArrayList<>(MOCK_DIB_DATA_LOOP_SIZE);
        for (int i = 0; i < MOCK_DIB_DATA_LOOP_SIZE; i++) {
            params.add(new User("xuliugen", "password" + i, "男", i));
        }
        return params;
    }

    class User {
        private String userName;
        private String password;
        private String sex;
        private int age;

        public User(String userName, String password, String sex, int age) {
            this.userName = userName;
            this.password = password;
            this.sex = sex;
            this.age = age;
        }
    }


}
