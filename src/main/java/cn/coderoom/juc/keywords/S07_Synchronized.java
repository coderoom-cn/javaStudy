package cn.coderoom.juc.keywords;

/**
 * 去掉注释，分析一下这个程序的输出
 * @package：cn.coderoom.juc.keywords
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S07_Synchronized extends Thread {

    private /*volatile*/ int count = 100;

    @Override
    public /*synchronized*/ void run() {
        count--;
        System.out.println(Thread.currentThread().getName() + " count = " + count);
    }

    public static void main(String[] args) {
        S07_Synchronized t = new S07_Synchronized();
        for(int i=0; i<100; i++) {
            new Thread(t, "THREAD" + i).start();
        }
    }

}
