package cn.coderoom.juc.apply;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 *
 * 最后启动所有检查判断该车能否可以发动并放行
 *
 * @package：cn.coderoom.juc.apply
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class BusCheckPoint_CheckStartUp {

    private static List<BusCheckPoint> stationList;
    private static CountDownLatch countDown;

    public BusCheckPoint_CheckStartUp() {
    }

    public static boolean checkAllStations() throws Exception {

        // 初始化3个调度站
        countDown = new CountDownLatch(2);

        // 把所有检查点添加进list
        stationList = new ArrayList<BusCheckPoint>();
        stationList.add(new BusCheckPoint_CheckTire(countDown));
        stationList.add(new BusCheckPoint_CheckWindow(countDown));

        // 使用线程池
        Executor executor = Executors.newFixedThreadPool(stationList.size());

        for (BusCheckPoint center : stationList) {
            executor.execute(center);
        }

        // 等待线程执行完毕，当计数减到0时，所有线程并行执行
        countDown.await();

        for (BusCheckPoint center : stationList) {
            if (!center.isOk()) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) throws Exception {
        boolean result = BusCheckPoint_CheckStartUp.checkAllStations();
        System.out.println("监控中心针对该车的检查结果为：" + result);
    }

}
