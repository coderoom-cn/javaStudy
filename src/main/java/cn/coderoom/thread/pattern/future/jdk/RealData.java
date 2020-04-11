package cn.coderoom.thread.pattern.future.jdk;

import java.util.concurrent.Callable;

/** 
 * 
 * @class RealData
 * @package cn.coderoom.thread.pattern.future.jdk
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/14 14:43
*/ 
public class RealData implements Callable<String> {

    protected String data;

    public RealData(String data) {
        this.data = data;
    }

    @Override
    public String call() throws Exception {
        //利用sleep方法来表示真是业务是非常缓慢的
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return data;
    }

}
