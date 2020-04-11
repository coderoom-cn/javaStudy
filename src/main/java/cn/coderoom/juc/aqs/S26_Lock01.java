package cn.coderoom.juc.aqs;

import java.util.concurrent.locks.Lock;

/**
 * @package：cn.coderoom.juc.aqs
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S26_Lock01 {

    public static int m = 0;
    public static Lock lock = new S26_Lock();

    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[100];

        for(int i=0; i<threads.length; i++) {
            threads[i] = new Thread(()->{
                try {
                    lock.lock();
                    for (int j = 0; j < 100; j++) m++;
                } finally {
                    lock.unlock();
                }
            });
        }

        for(Thread t : threads) t.start();

        for (Thread t : threads) t.join();

        System.out.println(m);
    }

}
