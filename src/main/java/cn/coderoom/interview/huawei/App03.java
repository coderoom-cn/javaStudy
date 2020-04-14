package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 * 写出一个程序，接受一个有字母和数字以及空格组成的字符串，和一个字符，然后输出输入字符串中含有该字符的个数。不区分大小写。
 * @package：cn.coderoom.interview.huawei
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class App03 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        String str = read.nextLine();
        str = str.substring(str.lastIndexOf(" ") + 1, str.length());
        System.out.println(str.length());
    }

    public void test(String[] args) {
        Scanner read = new Scanner(System.in);
        String str = read.next().toUpperCase();
        char c = read.next().toUpperCase().charAt(0);
        int sum = 0;
        for(int i = 0; i < str.length(); i++) {
            if(c == str.charAt(i)) {
                sum++;
            }
        }
        System.out.println(sum);
    }

}
