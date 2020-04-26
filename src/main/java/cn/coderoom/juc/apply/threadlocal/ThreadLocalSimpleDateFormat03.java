package cn.coderoom.juc.apply.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 在这段代码中，我们使用了ThreadLocal帮每个线程去生成它自己的simpleDateFormat对象，
 * 对于每个线程而言，这个对象是独享的。但与此同时，这个对象就不会创造过多，一共只有16个
 * @class ThreadLocalSimpleDateFormat03
 * @package cn.coderoom.juc.apply.threadlocal
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2019/4/26 14:07
*/
public class ThreadLocalSimpleDateFormat03 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    static ConcurrentHashMap<Integer, Integer> localMap = new ConcurrentHashMap<>();  // 确定返回数目
    static ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();    // SimpleDateFormat 对象数目
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(()->{
                String date = new ThreadLocalSimpleDateFormat03().date(finalI);
                map.put(date,0);
            });
        }
        threadPool.shutdown();
        TimeUnit.SECONDS.sleep(2);
        System.out.println("----------------------------"+map.size());      // 1000
        System.out.println("----------------------------"+localMap.size()); //  16
    }
    public String date(int seconds){
        Date date = new Date(1000 * seconds);
//        get()--->setInitialValue()--->initialValue()
        SimpleDateFormat dateFormat = dateFormatThreadLocal.get();
        int i = System.identityHashCode(dateFormat);
        localMap.put(i,0);  // 验证dateFormat对象是不唯一的
        return dateFormat.format(date);
    }
    //    ThreadLocal
    public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal = new ThreadLocal<SimpleDateFormat>(){
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("mm:ss");
        }
    };
//  public static ThreadLocal<SimpleDateFormat> dateFormatThreadLocal =
//                            ThreadLocal.withInitial(() -> new SimpleDateFormat("mm:ss"));

}
