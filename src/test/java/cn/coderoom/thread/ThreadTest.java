package cn.coderoom.thread;

import org.junit.Test;

/**
 * 
 * @class ThreadTest
 * @package cn.coderoom.thread
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/9 11:34 
*/ 
public class ThreadTest {

    @Test
    public void aliveOtherThread(){
        ExampleThread exampleThread = new ExampleThread();
        exampleThread.complexCurrentThread();
    }

}