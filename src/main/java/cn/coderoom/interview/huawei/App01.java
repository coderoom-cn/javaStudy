package cn.coderoom.interview.huawei;

import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @package：cn.coderoom.interview.huawei
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class App01 {


    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()){
            TreeSet<Integer> treeSet = new TreeSet<>();
            int nextInt = sc.nextInt();
            for (int i = 0; i < nextInt; i++) {
                treeSet.add(sc.nextInt());
            }
            for (Integer integer : treeSet) {
                System.out.println(integer);
            }
        }
    }

}
