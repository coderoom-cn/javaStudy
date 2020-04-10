package cn.coderoom.juc.apply;

import java.util.concurrent.CountDownLatch;

/**
 * 检查轮胎
 * @package：cn.coderoom.juc.apply
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class BusCheckPoint_CheckTire extends BusCheckPoint {

    public BusCheckPoint_CheckTire(CountDownLatch countDown) {
        super(countDown, "检查轮胎");
    }

    @Override
    public void check() {
        System.out.println("正在检查 [" + this.getStation() + "]...");

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("检查 [" + this.getStation() + "] 完毕，可以发车~");
    }

}
