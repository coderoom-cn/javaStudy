package cn.coderoom.thread.apply.animal;

/**
 *
 * @package: cn.coderoom.thread.apply.animal
 * @class: Tortoise
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2020/4/21 22:00
 */
public class Tortoise extends Animal {

    public Tortoise() {
        setName("乌龟");// Thread的方法，给线程赋值名字
    }

    // 重写running方法，编写乌龟的奔跑操作
    @Override
    public void runing() {
        // 乌龟速度
        int dis = 2;
        length -= dis;
        System.out.println("乌龟跑了" + dis + "米，距离终点还有" + length + "米");
        if (length <= 0) {
            length = 0;
            System.out.println("乌龟获得了胜利");
            // 让兔子不要在跑了
            if (calltoback != null) {
                calltoback.win();
            }
        }
        try {
            sleep(100);						//没0.1秒跑2米
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
