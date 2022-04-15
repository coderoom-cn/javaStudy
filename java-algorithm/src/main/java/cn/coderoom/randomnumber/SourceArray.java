package cn.coderoom.randomnumber;

import java.util.HashSet;
import java.util.Random;

/**
 * Random类的实例用于生成伪随机数流。此类使用 48 位的种子，使用线性同余公式对其进行修改(请参阅 Donald Knuth 的《The Art of Computer Programming， Volume 2》，第 3.2.1 节)。
 *
 * 定义一个源数组，将指定范围数字放入源数组，随机源数组的索引，每次随机范围减1，
 * 每次随机到的索引元素值放入结果集数组中，然后将随机到的索引元素值替换为当前随机的最大索引位置元素
 *
 *
 */
public class SourceArray {


    public static int[] randomNums3(int min, int max, int n) {
        int range = max - min + 1;
        if (min > max || n < 0 || n > range) {
            return null;
        }
        int[] nums = new int[n];
        int[] sources = new int[range];
        for (int i = 0; i < range; i++) {
            sources[i] = min + i;
        }
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int index = random.nextInt(range - i);
            nums[i] = sources[index];

            if (index != range - i - 1) {
                sources[index] = sources[range - i - 1];
            }
        }
        return nums;
    }

    public static HashSet randomNums(int min, int max, int n) {

        HashSet result = new HashSet();
        // 将随机到的索引元素值替换为当前随机的最大索引位置元素
        int range = max - min + 1;
        if (min > max || n < 0 || n > range) {
            return null;
        }
        /**
         * 索引数组
         */
        int[] sources = new int[range];
        for (int i = 0; i < range; i++) {
            sources[i] = min + i;
        }
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            // 该方法的作用是生成一个随机的int值，该值介于[0,n)的区间，也就是0到n之间的随机int值，包含0而不包含n。
            int index = random.nextInt(range - i);
            result.add(sources[index]);

            if (index != range - i - 1) {
                // 将随机到的索引元素值替换为当前随机的最大索引位置元素
                sources[index] = sources[range - i - 1];
            }
        }
        return result;
    }


    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        int min = 1, max = 1_000_000, n = max - min+1;
        HashSet result = SourceArray.randomNums(min, max, n);
        System.out.println("================="+ (System.currentTimeMillis() - startTime) );
        System.out.println("=================" + result.size());
    }

}
