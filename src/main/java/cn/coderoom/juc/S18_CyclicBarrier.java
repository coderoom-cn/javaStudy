package cn.coderoom.juc;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 *
 * 栅栏类似于闭锁，它能阻塞一组线程直到某个事件的发生。栅栏与闭锁的关键区别在于，所有的线程必须同时到达栅栏位置，才能继续执行。<br/>
 * 闭锁用于等待事件，而栅栏用于等待其他线程。CyclicBarrier可以使一定数量的线程反复地在栅栏位置处汇集。<br/>
 * 当线程到达栅栏位置时将调用await方法，这个方法将阻塞直到所有线程都到达栅栏位置。
 * 如果所有线程**都**到达栅栏位置，那么栅栏将打开，此时所有的线程都将被释放，而栅栏将被重置以便下次使用。
 * @package：cn.coderoom.juc
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S18_CyclicBarrier {

    public static void main(String[] args) {
        //CyclicBarrier barrier = new CyclicBarrier(20);

        CyclicBarrier barrier = new CyclicBarrier(20, () -> System.out.println("测试"));

        /*CyclicBarrier barrier = new CyclicBarrier(20, new Runnable() {
            @Override
            public void run() {
                System.out.println("ÂúÈË£¬·¢³µ");
            }
        });*/

        for(int i=0; i<100; i++) {

            new Thread(()->{
                try {
                    barrier.await();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }).start();

        }
    }

}
