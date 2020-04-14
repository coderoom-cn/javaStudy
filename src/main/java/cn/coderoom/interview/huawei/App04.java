package cn.coderoom.interview.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * 编写一个函数，计算字符串中含有的不同字符的个数。字符在ACSII码范围内(0~127)。不在范围内的不作统计。
 *
 * @package：cn.coderoom.interview.huawei
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class App04 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String input = read.next();
        char[] arr = input.toCharArray();
        Map<Character, Integer> result = new HashMap<>();
        int sum = 0;
        for(int i = 0; i < arr.length; i++) {
            if(!result.containsKey(arr[i])) {
                result.put(arr[i], 1);
                sum++;
            }
        }
        System.out.println(sum);
    }

}
