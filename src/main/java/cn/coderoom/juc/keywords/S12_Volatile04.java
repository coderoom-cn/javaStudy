package cn.coderoom.juc.keywords;

import java.util.ArrayList;
import java.util.List;

/**
 * 对比上一个程序，可以用synchronized解决，synchronized可以保证可见性和原子性，volatile只能保证可见性
 * @package：cn.coderoom.juc.keywords
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S12_Volatile04 {

    /*volatile*/ int count = 0;

    synchronized void m() {
        for (int i = 0; i < 10000; i++)
            count++;
    }

    public static void main(String[] args) {
        S12_Volatile04 t = new S12_Volatile04();

        List<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            threads.add(new Thread(t::m, "thread-" + i));
        }

        threads.forEach((o) -> o.start());

        threads.forEach((o) -> {
            try {
                o.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        System.out.println(t.count);

    }


}
