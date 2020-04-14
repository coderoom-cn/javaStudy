package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
 * @package：cn.coderoom.interview.huawei
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class App07 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        while(read.hasNext()) {
            float num = read.nextFloat();
            float temp = num;
            temp = temp - (int)temp;
            if(temp >= 0.5) {
                System.out.println((int)num + 1);
            } else {
                System.out.println((int)num);
            }
        }
    }

}
