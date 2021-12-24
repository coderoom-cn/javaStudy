package cn.coderoom.leetcode.basic.sorting;

public class InsertionSort {

    /**
     * 试代码使用的时间与内存。
     */
    public static long concurrentTime1, concurrentTime2, concurrentMemory1, concurrentMemory2;

    private static void start() {
        //得到程序开始时的系统时间&#xff08;纳秒级&#xff0c;最终转化毫秒&#xff0c;保留小数点后两位&#xff09;
        concurrentTime1 = System.nanoTime();
        //得到虚拟机运行、程序开始执行时jvm所占用的内存。
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory1 = runtime.totalMemory()-runtime.freeMemory();
    }

    private static void end() {
        //得到程序执行完毕时的系统时间&#xff08;毫秒级&#xff09;
        concurrentTime2 = System.nanoTime();
        //得到虚拟机运行、所要测试的执行代码执行完毕时jvm所占用的内存&#xff08;byte&#xff09;。
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory2 = runtime.totalMemory()-runtime.freeMemory();

        //计算start和end之间的代码执行期间所耗时间(ms)与内存(M)。
        // 1毫秒(ms) = 1000微秒(us) = 1000 000纳秒(ns)
        // 1M = 1*2^20 byte = 1024 * 1024 byte;
        String time = String.valueOf((double)(concurrentTime2 - concurrentTime1)/1000000);
        String memory = String.valueOf((double)(concurrentMemory2-concurrentMemory1)/1024/1024) ;
        System.out.println("---------------您的代码执行时间为：" + time.substring(0,time.equals("0.0") ? 1 : (time.indexOf(".")+3)) + " ms, 消耗内存：" + memory.substring(0,memory.equals("0.0") ? 1 : (memory.indexOf(".")+3)) + " M + !---------------");
    }

    public static void main(String[] args)
    {
        start();
        int[] arr={90,10,11,45,34,88};

        //排序前；
        System.out.println("原数组：");
        printArray(arr);

        //排序
        insertionSort(arr);
        System.out.println("升序排序后：");

        //排序后：
        printArray(arr);
        end();
    }
    /**
     * 插入排序:  升序为例
     * 原理：每次将最后一个元素作为插入元素，  与有序数列比较后 插入正确位置
     *
     */
    private static int[]  insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {  //i<6  1,2,3,4,5
            for (int j = i; j > 0; j--) {       //j=i  代表数组角标
                if (arr[j] < arr[j - 1]) {//符合条件，插入元素（交换位置）
                    swap(arr,j,j-1);
                }
            }
        }
        return arr;
    }
    /*
    发现无论什么排序。都需要对满足条件的元素进行位置置换。
    所以可以把这部分相同的代码提取出来，单独封装成一个函数。
    */
    public static void swap(int[] arr,int a,int b)
    {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void printArray(int[] arr)
    {
        System.out.print("[");
        for(int x=0; x<arr.length; x++)
        {
            if(x!=arr.length-1) {
                System.out.print(arr[x]+", ");
            } else {
                System.out.println(arr[x]+"]");
            }

        }
    }

}
