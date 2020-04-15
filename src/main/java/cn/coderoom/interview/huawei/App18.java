package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
 * 密码验证合格程序
 * 密码要求: 1.长度超过8位 2.包括大小写字母.数字.其它符号,以上四种至少三种 3.不能有相同长度超2的子串重复(长度超过2的子串)
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App18
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 21:08
 */
public class App18 {

    public static void main ( String[] args ) {
        Scanner in = new Scanner( System.in );
        while ( in.hasNextLine() ) {
            String s = in.nextLine();
            if ( check_length( s )) {
                if ( check_kinds( s ) ) {
                    if ( check_repeat( s ) ) {
                        System.out.println("OK");
                    }
                    else {
                        System.out.println("NG");
                    }
                }
                else {
                    System.out.println("NG");
                }
            }
            else {
                System.out.println("NG");
            }
        }
        in.close();
    }
    public static boolean check_length ( String str ) {
        if ( str.length() <= 8 ) {
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean check_kinds ( String str ) {
        int num = 0;
        int lowerletters = 0;
        int upperletters = 0;
        int elseletters = 0;
        for ( int i = 0 ; i < str.length() ; i++ ) {
            if ( Character.isDigit(str.charAt(i)) ) {
                num++;
                continue;
            }
            if ( Character.isLowerCase(str.charAt(i)) ) {
                lowerletters++;
                continue;
            }
            if ( Character.isUpperCase(str.charAt(i)) ) {
                upperletters++;
                continue;
            }
            else {
                elseletters++;
            }
        }
        if ( ( num != 0 && lowerletters != 0 && upperletters != 0 ) || ( elseletters != 0 && lowerletters != 0 && upperletters != 0 )
                || ( num != 0 && elseletters != 0 && upperletters != 0 ) || (num != 0 && lowerletters != 0 && elseletters != 0 )) {
            return true;
        }
        else {
            return false;
        }
    }
    public static boolean check_repeat ( String str ) {
        int num = 0;
        for ( int i = 3 ; i <= str.length()/2 ; i++ ) {
            for ( int j = 0 ; j < str.length() - i ; j++ ) {
                String s = str.substring(j, j + i );
                if ( str.indexOf(s) != str.lastIndexOf(s) ) {
                    num++;
                }
            }
        }
        if ( num == 0) {
            return true;
        }
        else {
            return false;
        }
    }

}
