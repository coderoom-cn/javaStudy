package cn.coderoom.juc.aqs;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * 自定义独占锁测试
 *
 * @package：cn.coderoom.juc.aqs
 * @author: lim
 * @email:coderoom.cn@gmail.com
 */
public class S26_Lock02_test {

    private S26_Lock02 lock = new S26_Lock02();

    public void getOrderNo() {
        try {
            /**
             * 用lock.lock() 来占有锁.
            */
            lock.lock();
            System.out.println(Thread.currentThread().getName() + "--" + SimpleDateFormat.getTimeInstance(SimpleDateFormat.FULL).format(new Date()));
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            /**
             * 释放锁
            */
            lock.unlock();
        }
    }

    /**
     * 通过开启2个线程来对synOrder中的getOrderNo产生竞争，导致阻塞。
     * 可以清楚的见到实际上 用AQS来实现自定义的锁，在已有的concurrent包下已够用
    */
    public static void main(String[] args) throws InterruptedException {
        S26_Lock02_test synOrder = new S26_Lock02_test();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synOrder.getOrderNo();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synOrder.getOrderNo();
            }
        });
        System.out.println("t1:"+t1.getName());
        System.out.println("t2:"+t2.getName());
        t1.start();
        t2.start();
        Thread.currentThread().join();
    }

}
