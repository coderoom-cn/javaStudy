package cn.coderoom.thread;

/** 
 * 
 * @class ExampleShareDataThread
 * @package cn.coderoom.thread
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/9 16:44 
*/ 
public class ExampleShareDataThread extends Thread {

    /**
     * 全局变量
    */
    private int count = 10;

    @Override
    public void run() {
        /**
         * 局部变量
        */
        int  partialNum = 10;
        super.run();
        while (count > 0) {
            System.out.println("while== "+currentThread().getName() + ",count=" + count);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**
             * ++、-- 都不是原子操作，是3个原子操作组合
             * 1.读取主存中的count值，赋值给一个局部成员变量tmp
             * 2.tmp+1
             * 3.将tmp赋值给count
            */
            //count--;
            count = count -1;
            partialNum--;
            System.out.println(currentThread().getName() + ",count=" + count);
            //System.out.println(currentThread().getName() + ",partialNum=" + partialNum);
        }

    }

}
