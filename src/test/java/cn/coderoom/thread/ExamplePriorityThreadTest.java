package cn.coderoom.thread;

import org.junit.Test;

/**
 *  线程优先级
 * @class ExamplePriorityThreadTest
 * @package cn.coderoom.thread
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/9 11:59
*/
public class ExamplePriorityThreadTest {

    @Test
    public void testRun() throws Exception {
        ExamplePriorityThread threadA = new ExamplePriorityThread();
        threadA.setPriority(Thread.NORM_PRIORITY - 3 );
        threadA.start();
        ExamplePriorityThread threadB = new ExamplePriorityThread();
        threadB.setPriority(Thread.NORM_PRIORITY + 3);
        threadB.start();
        Thread.sleep(20000);
        threadA.interrupt();
        threadB.interrupt();

        /**
         * 超出了int的最大范围,
        */
        System.out.println("a=" + threadA.getCount());
        System.out.println("b=" + threadB.getCount());

    }

}