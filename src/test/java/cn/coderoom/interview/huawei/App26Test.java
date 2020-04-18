package cn.coderoom.interview.huawei;

import org.junit.Test;

import static org.junit.Assert.*;

public class App26Test {

    @Test
    public void removeKdigits() {

        String result = App26.removeKdigits("1325358",3);
        System.out.println(result);
    }
}