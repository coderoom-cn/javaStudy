package cn.coderoom.interview.huawei;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 删除字符串中出现次数最少的字符）（Java）
 *
 * 实现删除字符串中出现次数最少的字符，若多个字符出现次数一样，则都删除。输出删除这些单词后的字符串，字符串中其它字符保持原来的顺序。
 * 输入描述：
 * 字符串只包含小写英文字母, 不考虑非法输入，输入的字符串长度小于等于20个字节。
 * 输出描述：
 * 删除字符串中出现次数最少的字符后的字符串。
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App25
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 21:17
 */
public class App25 {

    public static void main (String[] args) {
        Scanner in = new Scanner( System.in );
        while ( in.hasNextLine() ) {
            String s = in.nextLine();
            int a[] = new int[26];
            for ( int i = 0 ; i < 26 ; i++) {
                a[i] = 0;
            }
            for ( int i = 0; i < s.length() ; i++ ) {
                String str = s.substring(0, i+1);
                if ( str.indexOf(s.charAt(i)) != -1) {
                    a[s.charAt(i)-97]++;
                }
            }
            int a1[] = new int[26];
            for ( int i = 0; i < 26; i++) {
                a1[i] = a[i];
            }
            Arrays.sort(a1);
            int n = 0;
            for ( int i = 0; i < 26; i++) {
                if ( a1[i] != 0 ) {
                    n = a1[i];
                    break;
                }
            }
            for ( int i = 0; i < 26; i++) {
                if ( a[i] == n ) {
                    s = s.replace((char)(i+97), '0');
                }
            }
            String s1[] = s.split("0");
            for( String i : s1) {
                System.out.print(i);
            }
            System.out.println();
        }
        in.close();
    }

}
