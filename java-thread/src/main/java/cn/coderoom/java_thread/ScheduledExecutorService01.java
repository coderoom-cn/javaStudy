package cn.coderoom.java_thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

public class ScheduledExecutorService01 {

    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);

        long initialDelay = 1;
        long period = 1;
        // 从现在开始1秒钟之后，每隔1秒钟执行一次job1

        //service.scheduleAtFixedRate(new MyScheduledExecutor("job1"), initialDelay, period, TimeUnit.SECONDS);

        // 从现在开始2秒钟之后，每隔2秒钟执行一次job2
        //service.scheduleWithFixedDelay(new MyScheduledExecutor("job2"), initialDelay, period, TimeUnit.SECONDS);
    }

}
