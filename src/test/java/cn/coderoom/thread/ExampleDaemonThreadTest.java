package cn.coderoom.thread;

import org.junit.Test;

/**
 * 守护线程
 * 守护线程是十分有用的，例如垃圾收集器，释放不再使用的对象的内存，并且从cache中移除那些不想要的入口。大多数虚拟机线程都是守护线程。
 * @class ExampleDaemonThreadTest
 * @package cn.coderoom.thread
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/9 11:53
*/
public class ExampleDaemonThreadTest {

    @Test
    public void testRun() throws Exception {

        ExampleDaemonThread thread = new ExampleDaemonThread();
        /**
         * 设置该线程**thread**为守护线程,必须在线程启动start()方法之前设置。用户线程（main）为ExampleDaemonThreadTest。
         *
         * 还有一个启动守护线程的方法就是利用Timer和TimerTask。
         * Timer是JDK提供的定时器工具，使用时会在主线程之外单独起一个线程执行指定的任务。
        */
        thread.setDaemon(true);
        thread.start();
        Thread.sleep(5000); //主线程休眠结束后守护线程同步停止。
        System.out.println("主线程ExampleDaemonThreadTest.testRun()停止，守护线程thread对象也不在打印了，也就停止了");

    }
}