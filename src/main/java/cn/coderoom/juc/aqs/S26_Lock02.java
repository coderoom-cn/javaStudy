package cn.coderoom.juc.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 *
 * 它在同一时刻只允许一个线程占有锁。S26_Lock02 定义了一个静态内部类，该内部类继承了同步器并实现了独占式获取和释放同步状态。
 * 在tryAcquire方法中，通过CAS方式设置同步器状态，如果设置成功，返回true，
 * 设置失败返回false。tryRelease(int arg)方法是将同步器状态设置为0
 *
 * 我们并没有直接和AbstractQueuedSynchronizer 而是 调用 S26_Lock02 提供的方法
 *
 * @package：cn.coderoom.juc.aqs
 * @author: lim
 * @email:coderoom.cn@gmail.com
 * @see S26_Lock02_test
 */
public class S26_Lock02 implements Lock {

    /**
     * 静态内部类，自定义同步器
    */
    private static class Sync extends AbstractQueuedSynchronizer {
        /**
         * 是否处于独占状态
        */
        @Override
        protected boolean isHeldExclusively() {
            return this.getState() == 1;
        }

        /**
         * 当状态为0时，获取锁，然后进行CAS设置同步状态
        */
        @Override
        protected boolean tryAcquire(int arg) {
            //compareAndSetState(int expect,int update):使用CAS设置当前状态，如果状态为expect则将状态修改为update
            if (compareAndSetState(0, 1)) {
                //标记正在拥有这个锁的线程
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }
            return false;
        }

        /**
         * 释放锁，将状态设置为0.
         *  独占式释放同步状态，等待获取同步状态的线程将有机会获取同步状态
        */
        @Override
        protected boolean tryRelease(int arg) {
            if (getState() == 0) {
                throw new IllegalMonitorStateException();
            }
            setExclusiveOwnerThread(null);
            setState(0);
            return true;
        }
        /**
         * 返回一个Condition,没给Condition都包含了一个Condition队列
        */
        Condition newCondition() {
            return new ConditionObject();
        }
    }


    private final Sync sync = new Sync();

    @Override
    public void lock() {
        // 独占式获取同步状态，如果当前线程获取同步状态成功，则由该方法返回。<br/>
        // 否则将会进入同步队列等待，该方法将会调用重写的tryAcquire(int arg)方法.<br/
        sync.acquire(1);
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        //与acquire(int arg)相同，但是该方法对中断有响应
        //如果线程未被中断且当前线程获取不到同步状态时，将会进入同步队列。否则如果线程被中断，则该方法会抛出InterruptedException异常
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        //在acquireInterruptibly(int arg)基础上增加了超时限制，如果当前线程在超时时间内没有获取到同步状态，将会返回false。获取到就返回true
        return sync.tryAcquireSharedNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        sync.release(0);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }

}
