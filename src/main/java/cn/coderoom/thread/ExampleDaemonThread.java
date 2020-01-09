package cn.coderoom.thread;

/** 
 * 守护线程
 * java提供了俩类的线程：用户线程和守护线程(user thread and Daemon thread)。用户线程是高优先级的线程。
 * JVM虚拟机在结束一个用户线程之前，会先等待该用户线程完成它的task。
 * 在另一方面，守护线程是低优先级的线程，它的作用仅仅是为用户线程提供服务。
 * 正是由于守护线程是为用户线程提供服务的，仅仅在用户线程处于运行状态时才需要守护线程,很好理解，没有被守护的对象了，也不需要守护线程了。
 * 另外，一旦所有的用户线程都运行完毕，那么守护线程是无法阻止JVM退出的。
 *
 * 注意：守护线程不能持有任何需要关闭的资源，例如打开文件等，因为虚拟机退出时，守护线程没有任何机会来关闭文件，这会导致数据丢失。
 * @class ExampleDaemonThread
 * @package cn.coderoom.thread
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/9 11:52 
*/ 
public class ExampleDaemonThread extends Thread {

    private int i = 0;

    @Override
    public void run() {
        super.run();
        try{
            while (true){
                i++;
                System.out.println("i=" + (i));
                Thread.sleep(1000);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
