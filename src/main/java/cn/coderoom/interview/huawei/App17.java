package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
 * 开发一个坐标计算工具， A表示向左移动，D表示向右移动，W表示向上移动，S表示向下移动。从（0,0）点开始移动，从输入字符串里面读取一些坐标，并将最终输入结果输出到输出文件里面。
 * 输入：
 * 输出描述：最终坐标，以,分隔
 * @package: cn.coderoom.interview.huawei
 * @class: App17
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 21:06
 */
public class App17 {

    public static void main ( String[] args ) {
        Scanner in = new Scanner( System.in );
        while ( in.hasNextLine() ) {
            String s1 = in.nextLine();
            String s[] = s1.split(";");
            int x = 0;
            int y = 0;
            for( int i = 0 ; i < s.length ; i++ ) {
                if ( (s[i].length() == 3 && ( (s[i].charAt(0) == 'A' || s[i].charAt(0) == 'S' || s[i].charAt(0) == 'W' || s[i].charAt(0) == 'D') &&
                        ( s[i].charAt(1) >= '0' && s[i].charAt(1) <= '9') && ( s[i].charAt(2) >= '0' && s[i].charAt(2) <= '9' ) )) ||
                        ( s[i].length() == 2 && ( (s[i].charAt(0) == 'A' || s[i].charAt(0) == 'S' || s[i].charAt(0) == 'W' || s[i].charAt(0) == 'D') &&
                                ( s[i].charAt(1) >= '0' && s[i].charAt(1) <= '9') ) ) ) {
                    if ( s[i].charAt(0) == 'A' ) {
                        x -= Integer.valueOf( s[i].substring(1) );
                    }
                    if ( s[i].charAt(0) == 'D' ) {
                        x += Integer.valueOf( s[i].substring(1) );
                    }
                    if ( s[i].charAt(0) == 'S' ) {
                        y -= Integer.valueOf( s[i].substring(1) );
                    }
                    if ( s[i].charAt(0) == 'W' ) {
                        y += Integer.valueOf( s[i].substring(1) );
                    }
                }
            }
            System.out.println( x +","+ y );
        }
    }

}
