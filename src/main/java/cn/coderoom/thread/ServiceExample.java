package cn.coderoom.thread;

/**
 * @package：cn.coderoom.thread
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/1/9
 */
public class ServiceExample {

    private int count = 10;

    public void cs(){

        while (count > 0) {
            System.out.println("while== count=" + count);
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            count = count -1;

            System.out.println(",count=" + count);
            //System.out.println(currentThread().getName() + ",partialNum=" + partialNum);
        }
    }

}
