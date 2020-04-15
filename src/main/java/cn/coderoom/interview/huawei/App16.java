package cn.coderoom.interview.huawei;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * 给定n个字符串，请对n个字符串按照字典序排列。
 *
 * 输入描述：输入第一行为一个正整数n(1≤n≤1000),下面n行为n个字符串(字符串长度≤100),字符串中只含有大小写字母。
 * 输出描述：数据输出n行，输出结果为按照字典序排列的字符串。
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App16
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 21:01
 */
public class App16 {

    public static void main ( String[] args ) {
        Scanner in = new Scanner( System.in );
        int n = in.nextInt();
        ArrayList<String> alist = new ArrayList<String>(n);
        for( int i = 0 ; i <= n ; i++ ) {
            alist.add(in.nextLine());
        }
        Collections.sort(alist);
//		for( String e : alist) {
//			System.out.print( e );
//		}
        for( int i = 0 ; i < n ; i++ ) {
            System.out.println(alist.get(i+1));
        }
        in.close();
    }

}
