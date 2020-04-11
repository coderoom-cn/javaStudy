package cn.coderoom.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * 通过AQS实现自定义锁，目前仅实现了lock和unlock
 *
 * @package：cn.coderoom.juc.aqs
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S26_Lock implements Lock {

    private S27_AQS sync = new S27_AQS();

    @Override
    public void lock() {
        sync.acquire(1);
    }

    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }

    @Override
    public Condition newCondition() {
        return null;
    }

}
