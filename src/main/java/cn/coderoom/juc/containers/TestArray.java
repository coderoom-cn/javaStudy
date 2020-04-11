package cn.coderoom.juc.containers;

import java.util.Arrays;

/**
 * @package：cn.coderoom.juc.containers
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/4/9
 */
public class TestArray {

    public static void main(String[] args) {
        int[] a = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Arrays.stream(a).map(i->i+1).forEach(i->System.out.print(i + " "));
    }

}
