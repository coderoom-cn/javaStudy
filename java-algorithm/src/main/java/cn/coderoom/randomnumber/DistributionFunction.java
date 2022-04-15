package cn.coderoom.randomnumber;

import java.util.Random;

/**
 * sqrt() 方法用于返回参数的算术平方根。
 * System.out.println("90 度的正弦值：" + Math.sin(Math.PI/2));
 * System.out.println("0度的余弦值：" + Math.cos(0));
 * System.out.println("60度的正切值：" + Math.tan(Math.PI/3));
 * System.out.println("1的反正切值： " + Math.atan(1));
 * System.out.println("π/2的角度值：" + Math.toDegrees(Math.PI/2));
 *
 * https://blog.csdn.net/ftfmatlab/article/details/124175933
 * https://diffyzh.github.io/2017/04/19/randomWeight/
 * https://www.heapdump.cn/article/3212372?fromComment=true
 * https://juejin.cn/post/6891077783301455880
 * https://wooyun.js.org/drops/%E8%81%8A%E4%B8%80%E8%81%8A%E9%9A%8F%E6%9C%BA%E6%95%B0%E5%AE%89%E5%85%A8.html
 *
 */
public class DistributionFunction {

    double rad, xc, yc;
    Random rand = new Random();

    /**
     *
     * @param radius 半径
     * @param x_center
     * @param y_center
     */
    public DistributionFunction(double radius, double x_center, double y_center)
    {
        rad = radius;
        xc = x_center;
        yc = y_center;
    }
    public double[] randPoint()
    {
        double d = rad * Math.sqrt(rand.nextDouble());
        double theta = rand.nextDouble() * 2 * Math.PI;

        return new double[]{d * Math.cos(theta) + xc, d * Math.sin(theta) + yc};
    }
    public double[] GetPoints() {
        int maxValue = (int) Math.ceil(rad);
        double[] result = new double[maxValue];
        maxValue -= 2;
        for (int i = 0; i < maxValue; i++) {
            result[i] = randPoint()[0];
            result[i+1] = randPoint()[1];
        }
        return result;
    }

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

    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        int min = 1, max = 1_000_000_0, n = max - min+1;
        new DistributionFunction(max, 1, 1).GetPoints();
        long secondTime = System.currentTimeMillis();
        System.out.println("================="+ (secondTime - startTime) );
        int[] ints = SourceArray.randomNums3(min, max, n);
        System.out.println("================="+ (System.currentTimeMillis() - secondTime) );

    }
}
