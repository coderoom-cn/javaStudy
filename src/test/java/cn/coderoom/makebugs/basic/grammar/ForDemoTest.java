package cn.coderoom.makebugs.basic.grammar;

import org.junit.Test;

/** 
 *  需求：java变量声明在循环体内还是循环体外的争论
 *  结论：对于Java来说，在循环外申明变量，效率不会变高。在循环外申明变量，内存占用会更大。根据业务实际情况而来：
 *  1.写在循环外延长了变量的生命周期，可能对其他变量命名带来冲突，而且确实在java中存在GC延缓回收的问题。
 *  2.对于基本数据类型，如int，声明在循环内和循环外，占用的都是栈内存，对真个内存管理控制意义不大。
 *  3.对于引用数据类型，写在循环内部确实多次开辟了内存，new出对象，消耗了内存。综上，不考虑变量生命周期，写在循环外较好。
 * @class ForDemoTest
 * @package cn.coderoom.makebugs.basic.grammar
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/4/2 11:38 
*/ 
public class ForDemoTest {

    ForDemo forDemo = new ForDemo();
    @Test
    public void testFor00() throws Exception {
        forDemo.testFor00();
    }

    @Test
    public void testFor01() throws Exception {
        forDemo.testFor01();
    }

}