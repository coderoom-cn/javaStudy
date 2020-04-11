package cn.coderoom.jvm.gc;

/**
 *
 * 测试常量引用对象作为GCRoots
 * 注意：t修饰符如果只是final会被回收，static final不会被回收，所以static final 才是常量的正确写法
 * 前置条件： -Xms1024m -Xmx1024m -Xmn512m -XX:+PrintGCDetails
 *
 * @class GCRootsConstant
 * @package cn.coderoom.jvm.gc
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/4/2 12:47
*/
public class GCRootsConstant {

    private static int _10MB = 10 * 1024 * 1024;
    private static final GCRootsConstant t = new GCRootsConstant(8 * _10MB);
    private byte[] memory;

    public GCRootsConstant(int size) {
        memory = new byte[size];
    }

    /**
     * t3被置为null，Minor GC后t3之前引用的对象（40M）被完全回收；
     * t为常量，存放于方法区中，引用了对象（80M），在Minor GC后，被转移到老年代中，且在Full GC后，也不会被回收，继续保留在老年代中。
     * @param args
     * @author coderoom.cn@gmail.com
     * @date 2020/4/2 13:17
     * @return void
    */
    public static void main(String[] args) {
        GCRootsConstant t3 = new GCRootsConstant(4 * _10MB);
        t3 = null;
        System.gc();
    }


}
