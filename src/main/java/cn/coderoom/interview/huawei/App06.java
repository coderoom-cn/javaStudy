package cn.coderoom.interview.huawei;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * 数据表记录包含表索引和数值，请对表索引相同的记录进行合并，即将相同索引的数值进行求和运算，输出按照key值升序进行输出。
 * @package：cn.coderoom.interview.huawei
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class App06 {

    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int num = read.nextInt();
        Map<Integer, Integer> map = new TreeMap<>();
        for(int i = 0; i < num; i++) {
            int key = read.nextInt();
            int value = read.nextInt();
            if(map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else {
                map.put(key, value);
            }
        }
        for(Map.Entry<Integer, Integer> entry:map.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }

}
