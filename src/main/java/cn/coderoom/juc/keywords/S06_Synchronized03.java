package cn.coderoom.juc.keywords;

/**
 *
 * @package：cn.coderoom.juc.c00
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S06_Synchronized03 {

    private static int count = 10;

    public synchronized static void m() { //这里等同于synchronized(FineCoarseLock.class)
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void mm() {
        synchronized(S06_Synchronized03.class) { //考虑一下这里写synchronized(this)是否可以？
            count --;
        }
    }

}
