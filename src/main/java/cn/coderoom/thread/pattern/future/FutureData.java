package cn.coderoom.thread.pattern.future;

/** 
 * FutureData是Future模式的关键，它实际上是真实数据RealData的代理，封装了获取RealData的等待过程。
 * @class FutureData
 * @package cn.coderoom.thread.pattern.future
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/14 14:03 
*/ 
public class FutureData implements Data {

    /**
     * FutureData是RealData的封装
     */
    private RealData realData;
    /**
     *  RealData对象是否装载
    */
    private boolean isReady = false;

    public synchronized void setRealData(RealData realData){
        /**
         * 如果已经装载完毕则直接返回
        */
        if(isReady){
            return;
        }
        /**
         * 如果未装载，进行装载真实数据
        */
        this.realData = realData;
        isReady = true;
        /**
         * RealData已经被注入到FutureData中了，通知getResult()方法
        */
        notify();
    }

    @Override
    public synchronized String getRequest() {

        /**FutureData.getRequest开始执行>>>>>>>>>>线程阻塞
         * 如果未装载好一直处于阻塞状态
         * 一直等到RealData注入到FutureData中
        */
        while (!isReady){
            System.out.println("FutureData.getRequest开始执行>>>>>>>>>>线程阻塞");
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /**
         * 装载好直接返回数据即可
        */
        return this.realData.getRequest();
    }

}
