package cn.coderoom.jvm.gc;

/** 
 * GCRoots 测试：虚拟机栈（栈帧中的局部变量）中引用的对象，作为GCRoots
 *
 * 前置条件：-Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 * 扩展：虚拟机栈中存放了编译器可知的八种基本数据类型,对象引用,returnAddress类型（指向了一条字节码指令的地址）
 * @class GCRoot
 * @package cn.coderoom.jvm.gc
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/4/2 12:38 
*/ 
public class GCRootsStackVariables {

    private int _10MB = 10 * 1024 * 1024;
    private byte[] memory = new byte[8 * _10MB];

    public static void main(String[] args) {
        method01();
        System.out.println("返回main方法");
        System.gc();
        System.out.println("第二次GC完成");
    }

    /**
     * 第一次GC：
     * GCRootsStackVariables t为局部变量，引用了new出的对象（80M），作为GC Roots，在Minor GC后被转移到老年代中，且Full GC也不会回收该对象，仍保留在老年代中。
     * 第二次GC：
     * method01方法执行完后，局部变量t跟随方法消失，不再有引用类型指向该对象，该对象在Full GC后，被完全回收，老年代腾出该对象之前所占的空间。
     * @author coderoom.cn@gmail.com
     * @date 2020/4/2 13:04
     * @return void
    */
    public static void method01() {
        GCRootsStackVariables t = new GCRootsStackVariables();
        System.gc();
        System.out.println("第一次GC完成");
    }


}
