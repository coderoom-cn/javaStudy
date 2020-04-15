package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
 * 写出一个程序，接受一个由字母和数字组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
 *
 * 输入描述：输入一个有字母和数字以及空格组成的字符串，和一个字符。
 * 输出描述：输出输入字符串中含有该字符的个数。
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App11
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 20:23
 */
public class App11 {

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String str1 = in.nextLine();
        int n = 0;
        String str2 = in.nextLine();

        String s1 = str1.toLowerCase();
        String s2 = str2.toLowerCase();

        for (int i = 0;i < s1.length();i++) {
            if ( s2.equals(s1.substring( i , i+1 )) ){
                n = n + 1;
            }
        }
        System.out.println(n);
    }

}
