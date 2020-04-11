package cn.coderoom.thread.pattern.future.jdk;

import java.util.concurrent.*;

/**
 * @package：cn.coderoom.thread.pattern.future.jdk
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/1/14
 */
public class FutureMain {

    public static void main(String[] args) throws Exception {
        /**
         * FutureTask 相当于FutureClient
         * FutureTask类同时实现类Runnable接口和Future接口。
         * 因此，FutureTask类技能拥有Runnable接口提供的异步计算能力，也能拥有Future接口提供的返回值给调用方的Future对象取消任务的能力。
        */
        FutureTask<String> futureTask =
                new FutureTask<String>(new RealData("name"));
        /**
         * 使用线程池,不建议使用ExecutorService executor = Executors.newFixedThreadPool(1)创建线程池
        */
        ExecutorService executor =
                new ThreadPoolExecutor(1,1,1000, TimeUnit.SECONDS,new LinkedBlockingQueue<Runnable>());

        /**
         * 执行FutureTask，相当于上例中的client.request("name")发送请求
        */
        executor.submit(futureTask);

        /**
         * 这里可以用一个sleep代替对其他业务逻辑的处理
         * 在处理这些业务逻辑过程中，RealData也正在创建，从而充分了利用等待时间
        */
        Thread.sleep(2000);
        //使用真实数据
        //如果call()没有执行完成依然会等待
        System.out.println("数据=" + futureTask.get());
    }

}
