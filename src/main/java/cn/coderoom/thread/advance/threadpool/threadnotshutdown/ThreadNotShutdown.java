package cn.coderoom.thread.advance.threadpool.threadnotshutdown;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 运行后，查看jvm，会发现线程每2秒就增长一个。
 * @class ThreadNotShutdown
 * @package cn.coderoom.thread.advance.threadpool.threadnotshutdown
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/14 16:23 
*/ 
public class ThreadNotShutdown {

    public static void main(String[] args) {
        while (true) {
            try {
                ExecutorService service = Executors.newFixedThreadPool(1);
                service.submit(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(2000); ////模拟处理业务
                        } catch (InterruptedException e) {
                        }
                    }
                });
                service = null;
            } catch (Exception e) {

            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
            }
        }
    }


}
