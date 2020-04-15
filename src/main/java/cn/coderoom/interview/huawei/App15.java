package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 * 将一个英文语句以单词为单位逆序排放。例如“I am a boy”，逆序排放后为“boy a am I”
 * 所有单词之间用一个空格隔开，语句中除了英文字母外，不再包含其他字符
 *
 * 输入描述：将一个英文语句以单词为单位逆序排放。
 * 输出描述：得到逆序的句子
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App15
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 20:59
 */
public class App15 {

    public static void main ( String[] args ) {
        Scanner in = new Scanner( System.in );
        String s = in.nextLine();
        String newString = reverse( s );
        System.out.println( newString );
        in.close();
    }

    /**
     * 反转句子
     *
     * @param sentence 原句子
     * @return 反转后的句子
     */
    public static String reverse(String sentence) {
        String s[] = sentence.split(" ");
        String newString = "";
        for( int i = s.length-1 ; i >= 0 ; i-- ) {
            if( i > 0 ) {
                newString += s[i] + " ";
            }
            else {
                newString += s[i];
            }
        }
        return newString;
    }

}
