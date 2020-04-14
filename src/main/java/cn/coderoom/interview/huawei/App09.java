package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
 * 连续输入字符串，请按长度为8拆分每个字符串后输出到新的字符串数组；
 * 长度不是8整数倍的字符串请在后面补数字0，空字符串不处理。
 * @package：cn.coderoom.interview.huawei
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class App09 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        while(read.hasNext()) {
            String str = read.nextLine();
            while(str.length() >= 8) {
                System.out.println(str.substring(0, 8));
                str = str.substring(8, str.length());
            }
            if(str.length() > 0 && str.length() < 8) {
                str = str + "0000000";
                System.out.println(str.substring(0, 8));
            }
        }
    }

}
