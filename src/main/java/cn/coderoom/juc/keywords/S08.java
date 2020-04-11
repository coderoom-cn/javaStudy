package cn.coderoom.juc.keywords;

/**
 * 同步和非同步方法是否可以同时调用？
 * @package：cn.coderoom.juc.keywords
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S08 {

    public synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + " m1 start...");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m1 end");
    }

    public void m2() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " m2 ");
    }

    public static void main(String[] args) {
        S08 t = new S08();

		/*new Thread(()->t.m1(), "t1").start();
		new Thread(()->t.m2(), "t2").start();*/

        new Thread(t::m1, "t1").start();
        new Thread(t::m2, "t2").start();

		/*
		//1.8之前的写法
		new Thread(new Runnable() {
			@Override
			public void run() {
				t.m1();
			}

		});
		*/

    }

}
