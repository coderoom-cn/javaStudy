package cn.coderoom.juc.aqs;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @package：cn.coderoom.juc.aqs
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/4/9
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

        //synchronized 程序员的丽春院 JUC
    }

}
