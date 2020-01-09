package cn.coderoom.thread;

import org.junit.Test;

/**
 * 多线程全局变量（类变量）共享
 * 局部变量，线程间互不干扰
 *
 * 多线程在这里只是在同/异步角度上解决高并发问题的其中的一个方法手段，是在同一时刻利用计算机闲置资源的一种方式。
 * 多线程在解决高并发问题中所起到的作用就是使计算机的资源在每一时刻都能达到最大的利用率，不至于浪费计算机资源使其闲置。
 * @class ExampleShareDataThreadTest
 * @package cn.coderoom.thread
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/9 16:44 
*/ 
public class ExampleShareDataThreadTest {

    @Test
    public void testRun() throws Exception {

        ExampleShareDataThread shareData = new ExampleShareDataThread();
        new Thread(shareData,"A").start();
        new Thread(shareData,"B").start();
        new Thread(shareData,"C").start();
        new Thread(shareData,"D").start();
        new Thread(shareData,"E").start();
        //new Thread(shareData,"F").start();

        Thread.sleep(2000);

    }

    @Test
    public void testRun1() throws Exception {

        ServiceExample shareData = new ServiceExample();
        shareData.cs();
        ServiceExample shareData1 = new ServiceExample();
        shareData1.cs();
        ServiceExample shareData2 = new ServiceExample();
        shareData2.cs();
        ServiceExample shareData3 = new ServiceExample();
        shareData3.cs();
        ServiceExample shareData4 = new ServiceExample();
        shareData4.cs();
        //new Thread(shareData,"F").start();

        Thread.sleep(2000);

    }

}