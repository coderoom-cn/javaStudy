package cn.coderoom.juc.keywords;

/**
 * 对比S07_Synchronized，分析一下这个程序的输出
 *
 * @package：cn.coderoom.juc.keywords
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S07_Synchronized0 extends Thread{

    private int count = 10;

    @Override
    public synchronized void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {

        for(int i=0; i<5; i++) {
            S07_Synchronized0 t = new S07_Synchronized0();
            new Thread(t, "THREAD" + i).start();
        }
    }

}
