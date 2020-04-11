package cn.coderoom.thread.pattern.future;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 异步处理，汇总
 * @class FutureMain
 * @package cn.coderoom.thread.pattern.future
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/14 11:11 
*/ 
public class FutureMain {

    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(df.format(new Date()));

        FutureClient futureClient = new FutureClient();
        /**
         * 这里会立即返回，因为获取的是FutureData，而非RealData
         * 返回 FutureData
        */
        Data data = futureClient.request("hello,world");

        try {
            /**
             * 这里可以用一个sleep代替对其他业务逻辑的处理
             * 在处理这些业务逻辑过程中，RealData也正在创建，从而充分了利用等待时间
            */
            Thread.sleep(4000);
            System.out.println("请求发送成功...");
            System.out.println("干其他的事情...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /**
         * 返回 FutureData.getRequest
         * 使用真实数据
         */
        String result = data.getRequest();

        System.out.println("main请求结束data>>>>>>>>>>>>总耗时5s"+result);
        System.out.println(df.format(new Date()));
    }
    
}
