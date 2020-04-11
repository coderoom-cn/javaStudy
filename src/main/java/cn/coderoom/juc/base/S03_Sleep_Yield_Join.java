package cn.coderoom.juc.base;

/**
 * 测试Thread方法：sleep，yield，join。
 * @class S03_Sleep_Yield_Join
 * @package cn.coderoom.juc.base
 * @author lim
 * @email coderoom.cn@gmail.com
*/
public class S03_Sleep_Yield_Join {

    public static void main(String[] args) {
        // testSleep();
        // testYield();
        testJoin();
    }

    static void testSleep() {
        new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("A" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    static void testYield() {
        new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("A" + i);
                if(i%10 == 0) {
                    Thread.yield();
                }


            }
        }).start();

        new Thread(()->{
            for(int i=0; i<100; i++) {
                System.out.println("------------B" + i);
                if(i%10 == 0) {
                    Thread.yield();
                }
            }
        }).start();
    }

    static void testJoin() {
        Thread t1 = new Thread(()->{
            for(int i=0; i<50; i++) {
                System.out.println("t1A" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(()->{

            try {
                /**
                 * 当调用 t1.join() 方法后，t2 线程会进入等待，然后等待 t1 执行完之后再继续执行。
                */
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(int i=0; i<50; i++) {
                System.out.println("t2A" + i);
                try {
                    Thread.sleep(500);
                    //TimeUnit.Milliseconds.sleep(500)
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }

}
