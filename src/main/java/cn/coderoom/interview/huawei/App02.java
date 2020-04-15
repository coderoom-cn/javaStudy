package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
 * 写出一个程序，接受一个十六进制的数值字符串，输出该数值的十进制字符串。（多组同时输入 ）
 *
 * @package：cn.coderoom.interview.huawei
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class App02 {

    public static void main(String[] args) {

        Scanner sc=new Scanner(System.in);
        while (sc.hasNext()){
            String str=sc.next().substring(2);
            System.out.println(Integer.parseInt(str,16));
        }

    }

}
