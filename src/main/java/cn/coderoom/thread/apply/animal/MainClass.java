package cn.coderoom.thread.apply.animal;

public class MainClass {

    /**
     * 龟兔赛跑：2000米
     */
    public static void main(String[] args) {
        // 实例化乌龟和兔子
        Tortoise tortoise = new Tortoise();
        Rabbit rabbit = new Rabbit();
        // 回调方法的使用，谁先调用calltoback方法，另一个就不跑了
        LetOneStop letOneStop1 = new LetOneStop(tortoise);
        // 让兔子的回调方法里面存在乌龟对象的值，可以把乌龟stop
        rabbit.calltoback = letOneStop1;
        LetOneStop letOneStop2 = new LetOneStop(rabbit);
        // 让乌龟的回调方法里面存在兔子对象的值，可以把兔子stop
        tortoise.calltoback = letOneStop2;
        // 开始跑
        tortoise.start();
        rabbit.start();
    }

}
