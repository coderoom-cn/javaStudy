package cn.coderoom;

//-server -Xmn400M -XX:SurvivorRatio=9 -Xms1000M -Xmx1000M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintTenuringDistribution -XX:+PrintHeapAtGC -XX:+PrintReferenceGC -XX:+PrintGCApplicationStoppedTime -XX:+UseConcMarkSweepGC
/**
 *
 */
//jdk7.。

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PromotionTest {
    public static void main(String[] args) throws IOException {
        //模拟初始化资源场景
        List<Object> dataList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            dataList.add(new InnerObject());
        }
        //模拟流量进入场景
        for (int i = 0; i < 73; i++) {
            if(i == 72){
                System.out.println("Execute young gc...Adjust promotion threshold to 1");
            }
            new InnerObject();
        }
        System.out.println("-------------Execute full gc...dataList has been promoted to cms old space");
        //这里注意dataList中的对象在这次Full GC后会进入老年代
        System.gc();
    }
    public static byte[] createData(){
        int dataSize = 1024*1024*4;//4m
        byte[] data = new byte[dataSize];
        for (int j = 0; j < dataSize; j++) {
            data[j] = 1;
        }
        return data;
    }
    static class InnerObject{
        private Object data;

        public InnerObject() {
            this.data = createData();
        }
    }
}
