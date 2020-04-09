package cn.coderoom.juc.aqs;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * @package：cn.coderoom.juc.aqs
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S27_AQS extends AbstractQueuedSynchronizer {

    protected boolean tryAcquire(int arg) {
        if(compareAndSetState(0, 1)) {
            setExclusiveOwnerThread(Thread.currentThread());
            return true;
        }
        return false;
    }

    @Override
    protected boolean tryRelease(int arg) {
        setExclusiveOwnerThread(null);
        setState(0);
        return true;
    }

    @Override
    protected boolean isHeldExclusively() {
        return getState() == 1;
    }

}
