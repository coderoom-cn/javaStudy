package cn.coderoom.juc.apply.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 我们将传入一个int seconds,然后用定义的simpleDateFormat格式化然后返回。
 * 现在，1000 个线程都要用到 SimpleDateFormat：（用来模拟多线程，传入seconds，得到时间）
 *
 * 我们刚才所做的就是每个任务都创建了一个 simpleDateFormat 对象，也就是说，1000 个任务对应 1000 个 simpleDateFormat 对象。
 *
 * @class ThreadLocalSimpleDateFormat01
 * @package cn.coderoom.juc.apply.threadlocal
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2019/4/26 14:04
*/
public class ThreadLocalSimpleDateFormat01 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);  // 1000线程太多，用线程池
    static ConcurrentHashMap<String, Integer> localMap = new ConcurrentHashMap<>();   // 计数
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(()->{
                String date = new ThreadLocalSimpleDateFormat01().date(finalI);
                localMap.put(date,0);  // 添加，可以起到去重作用，
            });
        }
        threadPool.shutdown();
        TimeUnit.SECONDS.sleep(2);
        System.out.println(localMap.size());   // 打印出结果，是否是1000
    }
    public String date(int seconds){
        Date date = new Date(1000 * seconds);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        return simpleDateFormat.format(date);
    }


}
