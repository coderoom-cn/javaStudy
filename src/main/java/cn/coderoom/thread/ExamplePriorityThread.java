package cn.coderoom.thread;

/**
 * 线程优先级
 * @class ExamplePriorityThread
 * @package cn.coderoom.thread
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/9 11:51
*/
public class ExamplePriorityThread extends Thread {

    private int count = 0;

    @Override
    public void run() {
        super.run();
        while (true){
            count++;
            if(count==100000000){
                count=0;
            }
        }
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}
