package cn.coderoom.interview.huawei;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class App27Test {

    @Test
    public void mergeSort() {

        int[] arrays = {9, 2, 5, 1, 3, 2, 9, 5, 2, 1, 8};
        App27.mergeSort(arrays);
        System.out.println(Arrays.toString(arrays));
    }

}