package cn.coderoom.thread;

/** 
 * 
 * @class AliveOtherThread
 * @package cn.coderoom.thread
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/9 11:33 
*/ 
public class ExampleThread extends Thread {

    public ExampleThread(){
        System.out.println("AliveOtherThread--------begin");
        System.out.println("Thread.currentThread().getName=" + Thread.currentThread().getName());
        System.out.println("Thread.currentThread.isAlive()=" + Thread.currentThread().isAlive());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());
        System.out.println("AliveOtherThread--------end");
    }

    public void complexCurrentThread() {
        System.out.println("complexCurrentThread begin=========");
        System.out.println("Thread.currentThread().getName=" + Thread.currentThread().getName());

        System.out.println("this.getName()=" + this.getName());
        System.out.println("complexCurrentThread end===========");
    }

    @Override
    public void run() {
        super.run();
        System.out.println("run---------begin");
        System.out.println("Thread.currentThread().getName=" + Thread.currentThread().getName());
        System.out.println("Thread.currentThread().isAlive()=" + Thread.currentThread().isAlive());
        System.out.println("this.getName()=" + this.getName());
        System.out.println("this.isAlive()=" + this.isAlive());
        System.out.println("run---------end");

        /**
         * thread sleep
        */
        try{
            System.out.println("run threadName=" + currentThread().getName() + " begin");
            Thread.sleep(2000);
            System.out.println("run threadName=" + currentThread().getName() +  " end");
        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
