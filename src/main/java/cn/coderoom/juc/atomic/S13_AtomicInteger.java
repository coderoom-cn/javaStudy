package cn.coderoom.juc.atomic;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * 解决同样的问题的更高效的方法，使用原子类：AtomXXX类
 * 原子类本身方法都是原子性的，但不能保证多个方法连续调用是原子性的
 *
 * @package：cn.coderoom.juc.atomic
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S13_AtomicInteger {

    /*volatile*/ //int count1 = 0;

    AtomicInteger count = new AtomicInteger(0);

    /*synchronized*/ void m() {
        for (int i = 0; i < 10000; i++)
            //if count1.get() < 1000
            count.incrementAndGet(); //count1++
    }

    public static void main(String[] args) {
        S13_AtomicInteger t = new S13_AtomicInteger();

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
