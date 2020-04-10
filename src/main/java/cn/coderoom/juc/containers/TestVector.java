package cn.coderoom.juc.containers;

import java.util.Vector;

/**
 *
 * 因为synchronized 只能保证它所修饰的代码块线程安全。（单独使用Vector的这些方法是安全）
 * synchronized 一单修饰的代码块执行完，那么改对象就已经被释放。
 * 即：同步容器直接保证单个操作的线程安全性，但是无法保证复合操作的线程安全。
 *
 * @package：cn.coderoom.juc.containers
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class TestVector {

    private static Vector<Integer> vector=new Vector();

    public static void main(String[] args) {

        //while(true){

            for(int i=0;i<10;i++){
                vector.add(i); //往vector中添加元素
            }
            Thread removeThread=new Thread(new Runnable() {
                @Override
                public void run() {
                    //获取vector的大小, vector是动态变化的
                    for(int i=0;i<vector.size();i++){

                        System.out.println("removeThread第"+i+"次");

                        //当前线程让出CPU,使例子中的错误更快出现
                        Thread.yield();
                        //移除第i个数据 remove方法有可能抛出ArrayIndexOutOfBoundsException
                        vector.remove(i);
                    }
                }
            });
            Thread printThread=new Thread(new Runnable() {
                @Override
                public void run() {
                    //获取vector的大小
                    for(int i=0;i<vector.size();i++){
                        //当前线程让出CPU,使例子中的错误更快出现
                        Thread.yield();
                        //获取第i个数据并打印
                        System.out.println("printThread第"+i+"次"+vector.get(i));
                    }
                }
            });
            removeThread.start();
            printThread.start();
            //避免同时产生过多线程
            while(Thread.activeCount()>20);

        }
    //}

}
