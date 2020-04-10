package cn.coderoom.juc.apply;

import java.util.concurrent.CountDownLatch;

/**
 * @package：cn.coderoom.juc.apply
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class BusCheckPoint_CheckWindow extends BusCheckPoint {

    public BusCheckPoint_CheckWindow(CountDownLatch countDown) {
        super(countDown, "窗户检查");
    }

    @Override
    public void check() {
        System.out.println("正在检查 [" + this.getStation() + "]...");

        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("检查 [" + this.getStation() + "] 完毕，可以发车~");
    }

}
