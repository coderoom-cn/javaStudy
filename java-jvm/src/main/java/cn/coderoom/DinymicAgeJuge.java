package cn.coderoom;

/**
 *
 * VM 参数：-verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution
 *
 * -verbose:gc -Xms20m -Xmx20m -Xmn10m -Xlog:gc* -XX:SurvivorRatio=8
 *
 * 在JDK 9之前使用-XX：+PrintGCDetails，在JDK 9之后使用-Xlog:gc*
 * 在JDK 9前使用-XX:+PrintTenuring-Distribution，JDK 9之后使用-Xlog:gc+age=trace
 *
 * https://blog.csdn.net/testunit/article/details/103971909
 */
public class DinymicAgeJuge {

    private static final int _1MB = 1024 * 1024;

    @SuppressWarnings("unused")
    public static void testTenuringThreshold(){
        byte[] allocation1,allocation2,allocation3,allocation4;
        allocation1 = new byte[_1MB / 4];
        // allocation1 + allocation2 大于 survivor 空间的一半。
        allocation2 = new byte[_1MB / 4];
        allocation3 = new byte[_1MB * 4];
        allocation4 = new byte[_1MB * 4];
        allocation4 = null;
        allocation4 = new byte[_1MB * 4];
    }

    /**
     *
     * #jvm参数配置
     * -Xms25m #堆内存最小25m
     * -Xmx25m #堆内存最大值25m
     * -Xmn10m #年轻代大小
     * -Xss1m  #线程栈大小
     * -XX:+UseParNewGC #使用ParNew垃圾回收器
     * -XX:+PrintGCDetails #打印GC信息
     * -XX:+PrintGCDateStamps # 打印gc时间戳
     * -Xms25m -Xmx25m -Xmn10m -Xss1m -XX:+UseParNewGC -XX:+PrintGCDetails -XX:+PrintGCDateStamps
     * 1.在这里使用temp来作为年龄累计工具
     * 2.基于多次调整，使用85次循环为一轮的原因是，每85次左右就会执行一次gc
     * 3.为了构建多年龄段存活对象引入了temp来对各年龄段保活
     *
     * @throws InterruptedException
     */
    public static void useParNewCollector4() throws InterruptedException {
        // 1.survivor超过50%
        // 2.survivor中存活次数从低到高累计，在累计到n时，超过survivor的50%
        // 3.存活次数大于n次的对象，都将晋升老年代
        Thread.sleep(20000);
        //年龄累计工具
        int[][] temp =new int[9][];

        // 400kb的toOldForAge常驻内存，当YGC超过15次时，toOld会进入老年代
        // toOldForAge对象大小肯定是比400kb要大的，400kb是纯粹的数据的大小
        int i = 0;
        int[] toOldForAge =new int[100 * 1024];
        for(int j = 0; j < 1000; j++) {
            Thread.sleep(100);
        //一个int占4个字节，数组纯数据大小为 4*200*1024 = 800kb大小 大约0.8兆
        //年轻代大小为100m，按照8:1:1的比例，那么80m的内存最多能存102个arr对象
            int[] arr =new int[200 * 1024];
            if(j%85 == 0 && i<9){
                temp[i++] = arr;
            }
            System.out.println("第"+j+"次创建对象");
        }
    }

    public static void useParNewCollector2() throws InterruptedException {
        // 年轻代回收进入老年代 ： 年轻代回收后对象超过survivor区
        for (int i = 0; i < 1000; i++) {
            int[] arr = new int[300 * 1024];
            //Thread.sleep(500);
            int[] arr1 = new int[300 * 1024];
            //Thread.sleep(500);
            int[] arr4 = new int[300 * 1024];
            //Thread.sleep(500);
            int[] arr3 = new int[300 * 1024];
            //Thread.sleep(500);
            System.out.println("第"+i+"次创建对象");

        }
    }

    public static void oldGC() {
        byte[] array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = new byte[2 * 1024 * 1024];
        array1 = null;

        byte[] array2 = new byte[128 * 1024];
        byte[] array3 = new byte[128 * 1024];

        array3 = new byte[2 * 1024 * 1024];
        array3 = new byte[2 * 1024 * 1024];
        array3 = new byte[128 * 1024];
        array3 = null;

        byte[] array4 = new byte[2 * 1024 * 1024];

    }
    public static void main(String[] args) throws InterruptedException {
        //testTenuringThreshold();
        //useParNewCollector4();
        useParNewCollector2();
        System.gc();
    }
}
