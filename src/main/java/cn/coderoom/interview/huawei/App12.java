package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
 * 写出一个程序，接受一个正浮点数值，输出该数值的近似整数值。如果小数点后数值大于等于5,向上取整；小于5，则向下取整。
 * 输入描述：输入一个正浮点数值
 * 输出描述：输出该数值的近似整数值
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App12
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 20:33
 */
public class App12 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String num = in.nextLine();
        float re = 0;
        int n = num.indexOf(".");
        if(Integer.parseInt(num.substring(n+1,n+2))>5){
            re = Integer.parseInt(num.substring(0,n)) + 1;
        }else{
            re = Integer.parseInt(num.substring(0,n));
        }

        System.out.println(re);
    }

}
