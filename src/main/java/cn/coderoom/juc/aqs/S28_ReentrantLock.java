package cn.coderoom.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 *
 * @package：cn.coderoom.juc.aqs
 * @author: lim
 * @email:coderoom.cn@gmail.com
 * @see S26_Lock02_test
 */
public class S28_ReentrantLock {

    private static volatile int i = 0;

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        //synchronized (S28_ReentrantLock.class) {
        i++;
        //}

        lock.unlock();

    }

}
