package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
 * 输入一个整数，将这个整数以字符串的形式逆序输出
 * 程序不考虑负数的情况，若数字含有0，则逆序形式也含有0，如输入为100，则输出为001
 *
 * 输入描述：输入一个int整数
 * 输出描述：将这个整数以字符串的形式逆序输出
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App14
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 20:55
 */
public class App14 {

    public static void main ( String[] args ) {
        Scanner in = new Scanner( System.in );
        String s = in.nextLine();
        for( int i = s.length()-1; i >= 0; i--) {
            System.out.print(s.charAt(i));
        }
        in.close();
    }

}
