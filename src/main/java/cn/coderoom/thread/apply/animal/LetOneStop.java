package cn.coderoom.thread.apply.animal;

/**
 * 创建一个让动物线程停止的类，这里要实现回调接口
 * @package: cn.coderoom.thread.apply.animal
 * @class: LetOneStop
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2020/4/21 22:02
 */
public class LetOneStop implements Animal.Calltoback {

    // 动物对象
    Animal an;

    // 获取动物对象，可以传入兔子或乌龟的实例
    public LetOneStop(Animal an) {
        this.an = an;
    }

    // 让动物的线程停止
    @Override
    public void win() {
        // 线程停止
        an.stop();
    }


}
