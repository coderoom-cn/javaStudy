package cn.coderoom.interview.huawei;

import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * 数据表记录包含表索引和数值，请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 *
 * 输入描述：先输入键值对的个数，然后输入成对的index和value值，以空格隔开
 * 输出描述：输出合并后的键值对（多行）
 *
 * @package: cn.coderoom.interview.huawei
 * @class: App13
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/15 20:46
 */
public class App13 {

    public static void main (String[] args) {
        Scanner in = new Scanner(System.in);
        Map<Integer,Integer> map = new TreeMap<Integer,Integer>();
        int n = in.nextInt();
        //in.nextLine();
        map.put(in.nextInt(), in.nextInt());
        //in.nextLine();
        for(int i=1;i<n;i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            if(map.containsKey(a)) {
                int c = map.get(a);
//				map.remove(a);
                map.put(a, b+c);
            }
            else {
                map.put(a, b);
            }
        }
        Set<Integer> s = map.keySet();
        for(Integer i : s) {
            System.out.println(i+" "+map.get(i));
        }
    }

}
