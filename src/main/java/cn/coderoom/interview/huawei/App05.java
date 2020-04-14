package cn.coderoom.interview.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * 输入一个int型整数，按照从右向左的阅读顺序，返回一个不含重复数字的新的整数。
 *
 * @package：cn.coderoom.interview.huawei
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class App05 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int num = read.nextInt();
        List<Integer> list = new ArrayList<>();
        while(num > 0) {
            if(list.contains(num % 10)) {
                num = num / 10;
                continue;
            } else {
                list.add(num % 10);
                num = num / 10;
            }
        }
        for(Integer i:list) {
            System.out.print(i);
        }
    }


}
