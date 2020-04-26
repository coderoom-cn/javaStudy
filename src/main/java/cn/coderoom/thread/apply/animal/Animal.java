package cn.coderoom.thread.apply.animal;

/**
 *
 * @package: cn.coderoom.thread.apply.animal
 * @class: Animal
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2020/4/21 21:58
 */
public abstract class Animal extends Thread {

    public int length = 2000;// 比赛长度

    public abstract void runing();

    @Override
    public void run() {
        super.run();
        while (length > 0) {
            runing();
        }
    }

    // 在需要回调数据的地方（两个子类需要），声明一个接口
    public static interface Calltoback {
        public void win();
    }

    // 2.创建接口对象
    public Calltoback calltoback;


}
