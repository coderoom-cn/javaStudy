package cn.coderoom.jvm.tools;

import cn.coderoom.utils.jvm.PIDTool;
import org.junit.Test;

/**
 * @package：cn.coderoom.jvm.tools
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/1/8
 */
public class JvisualvmToolTest {
    
    /** 
     *
     * @param 	
     * @author coderoom.cn@gmail.com
     * @date 2020/1/8 15:09 
     * @return void
    */ 
    @Test
    public void useProcessBuilder() throws Exception {
        JvisualvmTool jvisualvmTool = new JvisualvmTool();
        jvisualvmTool.useProcessBuilder();
    }


    /**
     *  堆大小发生变化
     * @param
     * @author coderoom.cn@gmail.com
     * @date 2020/1/8 15:48
     * @return void
    */
    @Test
    public void forXha() throws Exception {

        // 为了能够看完整日志，等20s
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 1000000; i++) {
            JvisualvmTool jvisualvmTool = new JvisualvmTool();
            System.out.println(">>>>>>>>>>"+i);
        }

        // 为了能够看完整日志，等20s
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    @Test
    public void forXhaa() throws Exception {

        PIDTool.getPid();
        // 为了能够看完整日志，等20s
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 1000000; i++) {
            JvisualvmTool jvisualvmTool = new JvisualvmTool();
            System.out.println(">>>>>>>>>>"+i);
        }
        System.gc();

        // 为了能够看完整日志，等20s
        try {
            Thread.sleep(40000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void forXhb() throws Exception {

        // 为了能够看完整日志，等20s
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        JvisualvmTool jvisualvmTool = null;
        for (int i = 0; i < 1000000; i++) {
            jvisualvmTool = new JvisualvmTool();
            System.out.println(">>>>>>>>>>"+i);
        }
        // 为了能够看完整日志，等20s
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void forXhc() throws Exception {

        // 为了能够看完整日志，等20s
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        JvisualvmTool jvisualvmTool = null;
        for (int i = 0; i < 1000000; i++) {
            jvisualvmTool = new JvisualvmTool();
            System.out.println(">>>>>>>>>>"+i);
            jvisualvmTool = null;
        }

        // 为了能够看完整日志，等20s
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}