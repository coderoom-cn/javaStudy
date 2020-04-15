package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 * 简单密码
 *
 * 假设渊子原来一个BBS上的密码为zvbo9441987,为了方便记忆，他通过一种算法把这个密码变换成YUANzhi1987，这个密码是他的名字和出生年份，怎么忘都忘不了，而且可以明目张胆地放在显眼的地方而不被别人知道真正的密码。
 * 他是这么变换的，大家都知道手机上的字母： 1--1， abc--2, def--3, ghi--4, jkl--5, mno--6, pqrs--7, tuv--8 wxyz--9, 0--0,就这么简单，渊子把密码中出现的小写字母都变成对应的数字，数字和其他的符号都不做变换，
 * 声明：密码中没有空格，而密码中出现的大写字母则变成小写之后往后移一位，如：X，先变成小写，再往后移一位，不就是y了嘛，简单吧。记住，z往后移是a哦。
 *
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App19
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 21:11
 */
public class App19 {

    public static void main ( String[] args ) {
        Scanner in = new Scanner( System.in );
        while ( in.hasNextLine() ) {
            String s = in.nextLine();
            String str = "";
            for ( int i = 0 ; i < s.length() ; i++ ) {
                if ( s.charAt(i) >= 'a' && s.charAt(i) <= 'z' ) {
                    str += letters_digital ( s.charAt(i) );
                    continue;
                }
                if ( s.charAt(i) >= 'A' && s.charAt(i) <= 'Z' ) {
                    Character c = Character.toLowerCase(s.charAt(i));
                    if ( c == 'z') {
                        str += "a";
                    }
                    else {
                        int a = c+1;
                        Character c1 = (char) a;
                        str += String.valueOf(c1);
                    }
                    continue;
                }
                else {
                    str += String.valueOf(s.charAt(i));
                }
            }
            System.out.println( str );
        }
        in.close();
    }
    public static String letters_digital ( Character c ) {
        if ( c == 'a' || c == 'b' || c == 'c' ) {
            return "2";
        }
        if ( c == 'd' || c == 'e' || c == 'f' ) {
            return "3";
        }
        if ( c == 'g' || c == 'h' || c == 'i' ) {
            return "4";
        }
        if ( c == 'j' || c == 'k' || c == 'l' ) {
            return "5";
        }
        if ( c == 'm' || c == 'n' || c == 'o' ) {
            return "6";
        }
        if ( c == 'p' || c == 'q' || c == 'r' || c == 's' ) {
            return "7";
        }
        if ( c == 't' || c == 'u' || c == 'v' ) {
            return "8";
        }
        if ( c == 'w' || c == 'x' || c == 'y' || c == 'z' ) {
            return "9";
        }
        else {
            return null;
        }
    }

}
