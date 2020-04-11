package cn.coderoom.thread.advance.threadpool.threadnotshutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executors作为局部变量时，一定要记得调用executor.shutdown();来关闭线程池，如果不关闭，会有线程泄漏问题。
 * @class ThreadShutdown
 * @package cn.coderoom.thread.advance.threadpool.threadnotshutdown
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/14 16:24 
*/ 
public class ThreadShutdown {

    public static void main(String[] args) {
        while (true) {
            ExecutorService service = Executors.newFixedThreadPool(1);
            try {
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                        }
                    }
                });
            } catch (Exception e) {
            }finally{
                /**
                 * 手动关闭
                */
                service.shutdown();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
    }

}
