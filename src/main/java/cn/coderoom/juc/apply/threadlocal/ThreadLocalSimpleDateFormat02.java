package cn.coderoom.juc.apply.threadlocal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 但是这样做是没有必要的，因为这么多对象的创建是有开销的，并且在使用完之后的销毁同样是有开销的，
 * 而且这么多对象同时存在在内存中也是一种内存的浪费。
 * 最简单的就是使用一个就可以了，变为static 共享，但是存在线程不安全问题，所以加锁。
 * 但是，加锁相当排队，我们希望达到的效果是既不浪费过多的内存,同时又想保证线程安全。
 * 经过思考得出,可以让每个线程都拥有一个自己的 simple Date Format对象来达到这个目的,这样就能两全其美了。
 *
 * @class ThreadLocalSimpleDateFormat02
 * @package cn.coderoom.juc.apply.threadlocal
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2019/4/26 14:05
*/
public class ThreadLocalSimpleDateFormat02 {

    public static ExecutorService threadPool = Executors.newFixedThreadPool(16);
    static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
    static ConcurrentHashMap<String, Integer> localMap = new ConcurrentHashMap<>();
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            threadPool.submit(()->{
                String date = new ThreadLocalSimpleDateFormat02().date(finalI);
                localMap.put(date,0);
            });
        }
        threadPool.shutdown();
        TimeUnit.SECONDS.sleep(2);
//      3. synchronized 关键字，就会陷入一种排队的状态，多个线程不能同时工作
        System.out.println(localMap.size());
    }
    public String date(int seconds){
        Date date = new Date(1000 * seconds);
        String s = null;
        synchronized (ThreadLocalSimpleDateFormat02.class){   // 加锁，安全
            // 变为static 共享，但是存在线程不安全问题，所以加锁。
            s = simpleDateFormat.format(date);
        }
        return s;
    }

}
