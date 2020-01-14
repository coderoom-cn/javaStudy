package cn.coderoom.thread.apply;

import org.junit.Test;

/** 
 * 利用线程在特定的数组中找到最大值
 * @class TenThreadsTest
 * @package cn.coderoom.thread.apply
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/10 16:36 
*/ 
public class TenThreadsTest {

    @Test
    public void testRun() throws Exception {
        TenThreads[] threads = new TenThreads[10];
        int[][] bigMatrix = getBigHairyMatrix();
        int max = Integer.MIN_VALUE;

        // Give each thread a slice of the matrix to work with
        for (int i=0; i < 10; i++) {
            threads[i] = new TenThreads(bigMatrix[i]);
            threads[i].start();
        }

        // Wait for each thread to finish
        try {
            for (int i=0; i < 10; i++) {
                threads[i].join();
                max = Math.max(max, threads[i].getMax());
            }
        }
        catch (InterruptedException e) {
            // fall through
        }

        System.out.println("Maximum value was " + max);
    }

    static   int[][]   getBigHairyMatrix()
    {
        int[][]   matrix=new   int[10][10];
        for(int   i=0;i<10;i++)
            for(int   j=0;j<10;j++)
            {
                matrix[i][j]=(int)(Math.random()*100000);
            }
        return   matrix;
    }

}