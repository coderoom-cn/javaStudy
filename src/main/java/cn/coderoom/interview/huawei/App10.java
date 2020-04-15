package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
 * 计算字符串最后一个单词的长度，单词以空格隔开。
 *
 * 输入： 一行字符串，非空，长度小于5000。
 * 输出：整数N，最后一个单词的长度。
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App10
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 20:16
 */
public class App10 {

    public static void main (String[] args) {
        Scanner in = new Scanner( System.in );
        String word = in.nextLine();
        int n = word.lastIndexOf (" ");
        int a = word.indexOf(" ");
        if ( n == -1){

            System.out.println( word.length() );

        } else {
            String str = word.substring ( n ,word.length()-1 );
            System.out.println(str.length());
        }
    }

}
