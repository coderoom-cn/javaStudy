package cn.coderoom.thread.advance.threadpool.threadnotshutdown;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池数量检测
 * @class ThreadQuantityCheck
 * @package cn.coderoom.thread.advance.threadpool.threadnotshutdown
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/14 16:27 
*/ 
public class ThreadQuantityCheck {

    public static void main(String[] args) throws Exception {
        /**
         * 用于获取到本java进程，进而获取总线程数
        */
        RuntimeMXBean runtimeBean = ManagementFactory.getRuntimeMXBean();
        String jvmName = runtimeBean.getName();
        System.out.println("JVM Name = " + jvmName);
        long pid = Long.valueOf(jvmName.split("@")[0]);
        System.out.println("JVM PID  = " + pid);
        ThreadMXBean bean = ManagementFactory.getThreadMXBean();
        int n = 1000;
        for (int i = 0; i < n; i++) {
            ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
            for (int j = 0; j < 5; j++) {
                executor.execute(() -> {
                    System.out.println("当前线程总数为：" + bean.getThreadCount());
                });
            }
            /**
             * 这里ThreadPoolExecutor作为局部变量，若你不手动关闭：线程总数为 = 5006
             * 线程全部泄漏（一个线程都没有死，没有被回收），白白的浪费了内存。
            */
            //executor.shutdown();
        }
        Thread.sleep(10000);
        System.out.println("线程总数为 = " + bean.getThreadCount());
    }


}
