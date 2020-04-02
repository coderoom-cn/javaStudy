package cn.coderoom.jvm.gc;

/** 
 *
 * 测试方法区中的静态变量引用的对象，作为GCRoots
 * 前置条件：-Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 *
 * 扩展：方法区存与堆一样,是各个线程共享的内存区域,用于存放已被虚拟机加载的类信息,常量,静态变量,即时编译器编译后的代码等数据。
 *
 * @class GCRootsMethodVariables
 * @package cn.coderoom.jvm.gc
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/4/2 12:47 
*/ 
public class GCRootsMethodVariables {

    private static int _10MB = 10 * 1024 * 1024;
    private byte[] memory;

    private static GCRootsMethodVariables t;

    public GCRootsMethodVariables(int size) {
        memory = new byte[size];
    }

    /**
     * t2被置为null，Minor GC后t2之前引用的对象（40M）被完全回收；
     * t为静态变量，存放于方法区中，引用了对象（80M），
     * 在Minor GC后，被转移到老年代中，且在Full GC后，也不会被回收，继续保留在老年代中。
     * @author coderoom.cn@gmail.com
     * @date 2020/4/2 13:11
     * @return void
    */
    public static void main(String[] args) {
        GCRootsMethodVariables t2 = new GCRootsMethodVariables(4 * _10MB);
        t2.t = new GCRootsMethodVariables(8 * _10MB);
        t2 = null;
        System.gc();
    }


}
