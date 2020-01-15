package cn.coderoom.thread.pattern.future;

/** 
 * 
 * @class FutureClient
 * @package cn.coderoom.thread.pattern.future
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/14 11:11 
*/ 
public class FutureClient {

    public Data request(final String request) {
        /**
         * 初始化代理对象，先返回给客户端
        */
        final FutureData futureData = new FutureData();
        /**
         * RealData的构建很慢，所以放在单独的线程中运行
         * 启动一个新的线程去加载真实的数据，传递给这个代理对象
        */
        new Thread(new Runnable() {
            @Override
            public void run() {
                /**
                 * 处理真实业务
                */
                RealData realData = new RealData(request);
                /**
                 *  装载
                */
                futureData.setRealData(realData);
            }
        }).start();

        return futureData;
    }
    
}
