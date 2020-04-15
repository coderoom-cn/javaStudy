package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
 * 输入一个正整数，按照从小到大的顺序输出它的所有质数的因子（如180的质数因子为2 2 3 3 5 ）
 * 输入参数：输入一个long型整数
 * 输出描述：按照从小到大的顺序输出它的所有质数的因子，以空格隔开。最后一个数后面也要有空格。
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App08
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 20:31
 */
public class App08 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        while(read.hasNext()) {
            long num = read.nextLong();
            while(num != 1) {
                for(int i = 2; i <= num; i++) {
                    if(num % i == 0) {
                        System.out.print(i + " ");
                        num = num / i;
                        break;
                    }
                }
            }
        }
    }

}
