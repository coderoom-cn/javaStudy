package cn.coderoom.juc.keywords;

/**
 *
 * @package：cn.coderoom.juc.c00
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S06_Synchronized0 {

    private int count = 10;

    public void m() {
        synchronized(this) { //任何线程要执行下面的代码，必须先拿到this的锁
            count--;
            System.out.println(Thread.currentThread().getName() + " count = " + count);
        }
    }

}
