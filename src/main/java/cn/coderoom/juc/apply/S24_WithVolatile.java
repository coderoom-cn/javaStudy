package cn.coderoom.juc.apply;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *  曾经的面试题：
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 *
 * 给lists添加volatile之后，t2能够接到通知，但是，t2线程的死循环很浪费cpu，如果不用死循环，
 * 而且，如果在if 和 break之间被别的线程打断，得到的结果也不精确，
 * 该怎么做呢？
 *
 * @package：cn.coderoom.juc.apply
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S24_WithVolatile {

    //添加volatile，使t2能够得到通知
    //volatile List lists = new LinkedList();
    volatile List lists = Collections.synchronizedList(new LinkedList<>());

    public void add(Object o) {
        lists.add(o);
    }

    public int size() {
        return lists.size();
    }

    public static void main(String[] args) {

        S24_WithVolatile c = new S24_WithVolatile();
        new Thread(() -> {
            for(int i=0; i<10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

				/*try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
            }
        }, "t1").start();

        new Thread(() -> {
            while(true) {
                if(c.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();
    }

}
