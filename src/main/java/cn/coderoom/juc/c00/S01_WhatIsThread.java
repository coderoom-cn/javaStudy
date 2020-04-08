package cn.coderoom.juc.c00;

import java.util.concurrent.TimeUnit;

/**
 *
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
