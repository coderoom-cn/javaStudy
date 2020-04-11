package cn.coderoom.jvm.gc;

/**
 *
 *  测试成员变量引用对象是否可作为GCRoots
 *
 *  前置条件：-Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 *
 * @class GCRootsMemberVariables
 * @package cn.coderoom.jvm.gc
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/4/2 12:47
*/
public class GCRootsMemberVariables {

    private static int _10MB = 10 * 1024 * 1024;
    private GCRootsMemberVariables t;
    private byte[] memory;

    public GCRootsMemberVariables(int size) {
        memory = new byte[size];
    }

    /**
     *
     * t4被置为null，Minor GC后t4之前引用的对象（40M）被完全回收；
     * t为成员变量，也叫实例变量，不同于类变量（静态变量），
     * 前面讲到类变量是存储在方法区中，而成员变量是存储在堆内存的对象中的，和对象共存亡，所以是不能作为GC Roots的，
     * 从日志中也可看出t在MinorGC后，跟随t4一起被完全回收。不再占用任何空间。
     * @param args
     * @author coderoom.cn@gmail.com
     * @date 2020/4/2 13:18
     * @return void
    */
    public static void main(String[] args) {
        GCRootsMemberVariables t4 = new GCRootsMemberVariables(4 * _10MB);
        t4.t = new GCRootsMemberVariables(8 * _10MB);
        t4 = null;
        System.gc();
    }


}
