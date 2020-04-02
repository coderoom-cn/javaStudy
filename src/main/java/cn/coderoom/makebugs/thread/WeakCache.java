package cn.coderoom.makebugs.thread;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @package：cn.coderoom.makebugs.thread
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/4/2
 */
public class WeakCache {

    private void printReferenceQueue(ReferenceQueue<Object> referenceQueue) {
        WeakEntry sv;
        while ((sv = (WeakEntry) referenceQueue.poll()) != null) {
            System.out.println("引用队列中元素的key：" + sv.key);
        }
    }

    private static class WeakEntry extends WeakReference<Object> {
        private Object key;

        WeakEntry(Object key, Object value, ReferenceQueue<Object> referenceQueue) {
            //调用父类的构造函数，并传入需要进行关联的引用队列
            super(value, referenceQueue);
            this.key = key;
        }
    }

    public static void main(String[] args) {
        ReferenceQueue<Object> referenceQueue = new ReferenceQueue<>();
        User user = new User("xuliugen", "123456");
        WeakCache.WeakEntry weakEntry = new WeakCache.WeakEntry("654321", user, referenceQueue);
        System.out.println("还没被回收之前的数据：" + weakEntry.get());

        user = null;
        System.gc(); //强制执行GC
        System.runFinalization();

        System.out.println("已经被回收之后的数据：" + weakEntry.get());
        new WeakCache().printReferenceQueue(referenceQueue);
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
