package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
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

}
