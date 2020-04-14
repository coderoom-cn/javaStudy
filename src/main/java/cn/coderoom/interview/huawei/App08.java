package cn.coderoom.interview.huawei;

import java.util.Scanner;

/**
 *
 *
 * @package：cn.coderoom.interview.huawei
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class App08 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        while(read.hasNext()) {
            long num = read.nextLong();
            while(num != 1) {
                for(int i = 2; i <= num; i++) {
                    if(num % i == 0) {
                        System.out.print(i + " ");
                        num = num / i;
                        break;
                    }
                }
            }
        }
    }

}
