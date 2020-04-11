package cn.coderoom.thread;

/**
 * @package：cn.coderoom.thread
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/1/9
 */
public class ExampleRunable  implements Runnable {

    @Override
    public void run() {
        System.out.println("这是实现Runnable接口的类");
    }

}
