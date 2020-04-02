package cn.coderoom.jdk.rtjar.java.util.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 
 * @class ConcurrentHashMapTest
 * @package cn.coderoom.jdk.rtjar.java.util.concurrent
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/3/19 15:53 
*/ 
public class ConcurrentHashMapTest {

    /**
     *  一个多线程并发获取map中的值并加1
     *  ConcurrentHashMap多原子操作组合 (其他的集合类型也会如此)
    */
    @Test
    public void atomicOperationCombination() throws Exception {
        java.util.concurrent.ConcurrentHashMap<String, Integer> map = new java.util.concurrent.ConcurrentHashMap();
        /**
         * key - 1
         */
        map.put("key", 1);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {

            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    /**
                     * 原子操作，是线程安全的
                     */
                    int key = map.get("key") + 1;
                    /**
                     * 原子操作，是线程安全的
                     */
                    map.put("key", key);

                    /**
                     * 各自都是原子操作，是线程安全的。但是他们组合在一起的时候就会有问题了，A线程在进入方法后，
                     * 通过map.get("key")拿到key的值，
                     * 刚把这个值读取出来还没有加1的时候，线程B也进来了，那么这导致线程A和线程B拿到的key是一样的。
                     */
                }
            });

        }
        Thread.sleep(3000); //模拟等待执行结束
        System.out.println("------" + map.get("key") + "------");
        executorService.shutdown();
    }

    /**
     * atomicOperationCombination的改进
     * @param
     * @author coderoom.cn@gmail.com
     * @date 2020/3/19 16:09
     * @return void
    */
    @Test
    public void fullyAtomicOperation() throws Exception {
        java.util.concurrent.ConcurrentHashMap<String, AtomicInteger> map = new java.util.concurrent.ConcurrentHashMap<String,AtomicInteger>();
        AtomicInteger integer = new AtomicInteger(1);
        map.put("key", integer);
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    map.get("key").incrementAndGet();
                }
            });
        }
        Thread.sleep(3000); //模拟等待执行结束
        System.out.println("------" + map.get("key") + "------");
        executorService.shutdown();

    }


}