package cn.coderoom.juc.base;

import java.util.concurrent.TimeUnit;

/**
 * Thread implements Runnable
 * Thread.start()： 导致此线程开始执行; Java Virtual Machine调用此线程的run方法。
 * join() ：
 * run()：Thread类重写了Runnable接口的run()方法。
 *
 * https://blog.csdn.net/lxx19941220/article/details/92587043
 * @package：cn.coderoom.juc.c00
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S01_WhatIsThread {

    private static class T1 extends Thread {
        @Override
        public void run() {
            for(int i=0; i<10; i++) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("T1");
            }
        }
    }

    public static void main(String[] args) {
        //new T1().run();
        new T1().start();
        for(int i=0; i<10; i++) {
            try {
                TimeUnit.MICROSECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("main");
        }

    }

}
