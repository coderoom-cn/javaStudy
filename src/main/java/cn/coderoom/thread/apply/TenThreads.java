package cn.coderoom.thread.apply;

/** 
 * 利用线程在特定的数组中找到最大值
 * @class TenThreads
 * @package cn.coderoom.thread.apply
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/10 16:29 
*/ 
public class TenThreads extends Thread {

    int max = Integer.MIN_VALUE;
    int[] ourArray;

    public TenThreads(int[] ourArray) {
        this.ourArray = ourArray;
    }

    /**
     * 在特定的数组中找到最大值
     * @author coderoom.cn@gmail.com
     * @date 2020/1/10 16:32
     * @return void
    */
    @Override
    public void run() {
        for (int i = 0; i < ourArray.length; i++){

            max = Math.max(max, ourArray[i]);
        }
    }

    public int getMax() {
        return max;
    }
    
}
