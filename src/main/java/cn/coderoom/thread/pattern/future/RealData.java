package cn.coderoom.thread.pattern.future;

/**
 * @package：cn.coderoom.thread.pattern.future
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/1/14
 */
public class RealData implements Data {

    private String result;

    /**
     * 真实的业务处理
 * @param queryStr
     * @author coderoom.cn@gmail.com
     * @date 2020/1/14 16:54
     * @return
    */
    public RealData(String queryStr){
        System.out.println("根据参数: "+queryStr+" 进行业务处理，这是一个很耗时的操作！-----start");
        try {
            /**
             * 这个sleep我们可以看做在处理业务逻辑.
            */
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("业务处理完毕，获取结果-------end");
        result = "查询结果";
    }

    @Override
    public String getRequest() {

        return result;
    }

}
