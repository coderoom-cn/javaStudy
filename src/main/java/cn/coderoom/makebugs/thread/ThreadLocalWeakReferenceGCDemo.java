package cn.coderoom.makebugs.thread;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @package：cn.coderoom.makebugs.thread
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/4/2
 */
public class ThreadLocalWeakReferenceGCDemo {

    private static final int THREAD_LOOP_SIZE = 20;
    private static final int MAIN_THREAD_LOOP_SIZE = 30;

    private void test(){
        try {
            //等待连接JConsole
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 1; i < THREAD_LOOP_SIZE; i++) {
            ThreadLocal<Map<Integer, String>> threadLocal = new ThreadLocal<>();
            Map<Integer, String> map = new HashMap<>();
            map.put(i, "我是第" + i + "个ThreadLocal数据！");
            threadLocal.set(map);
            threadLocal.get();

            System.out.println("第" + i + "次获取ThreadLocal中的数据");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void test1(){
        ThreadLocal<Map<Integer, String>> threadLocal1 = new ThreadLocal<>();
        Map<Integer, String> map1 = new HashMap<>(1);
        map1.put(1, "我是第1个ThreadLocal数据！");
        threadLocal1.set(map1);

        ThreadLocal<Map<Integer, String>> threadLocal2 = new ThreadLocal<>();
        Map<Integer, String> map2 = new HashMap<>(1);
        map2.put(2, "我是第2个ThreadLocal数据！");
        threadLocal2.set(map2);

        /**
         * 虽然这里我们自己定义了30个ThreadLocal变量，但是最后的确只有14个，其中还有三个是属于其他的，还有一点值得注意的是，
         * 我们的threadLocal1和threadLocal2 变量，在进行GC垃圾回收的时候，弱引用的Key是没有进行回收的，最后存活了下来！
         * 使得我们最后通过get方法可以获取到正确的数据。
         */
        for (int i = 3; i <= MAIN_THREAD_LOOP_SIZE; i++) {
            ThreadLocal<Map<Integer, String>> threadLocal = new ThreadLocal<>();
            Map<Integer, String> map = new HashMap<>(1);
            map.put(i, "我是第" + i + "个ThreadLocal数据！");
            threadLocal.set(map);
            threadLocal.get();

            if (i > 20) {
                //-Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8
                //会触发GC
                byte[] allocation1, allocation2, allocation3, allocation4;
                allocation1 = new byte[2 * 1024 * 1024];
                allocation2 = new byte[2 * 1024 * 1024];
                allocation3 = new byte[2 * 1024 * 1024];
                allocation4 = new byte[4 * 1024 * 1024];
            }
        }
        System.out.println("-------" + threadLocal1.get());
        System.out.println("-------" + threadLocal2.get());
    }

    private void test2(){

        User user = new User("hello", "123");
        WeakReference<User> userWeakReference = new WeakReference<>(user);
        System.out.println(userWeakReference.get());
        //另一种方式触发GC，强制执行GC
        System.gc();
        System.runFinalization();
        System.out.println(userWeakReference.get());

    }
    public static void main(String[] args) throws InterruptedException {


    }


    public static class User {
        private String userName;
        private String userPwd;
        //省去全参构造方法和toString()方法

        public User(String userName, String userPwd) {
            this.userName = userName;
            this.userPwd = userPwd;
        }
    }

}
