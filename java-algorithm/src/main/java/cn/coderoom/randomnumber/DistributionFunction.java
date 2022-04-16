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
 * https://codeantenna.com/a/0kZWpKLZhT
 * https://www.jianshu.com/p/99300452446f
 * https://wooyun.js.org/drops/%E8%81%8A%E4%B8%80%E8%81%8A%E9%9A%8F%E6%9C%BA%E6%95%B0%E5%AE%89%E5%85%A8.html
 * LeetCode 478 在圆内随机生成点
 *
 * 圆的方程表示为 (x - a) ^ 2 + (y - b) ^ 2 = r ^ 2，这里的 (a, b) 是圆心位置，r为半径 ，(x,y)为圆周上的任意点。
 * 是的，圆还可以用极坐标的形式来表示，只需随机出一个角度 theta，再随机出一个小于半径的长度，这样就可以得到圆中的坐标位置了。
 * 然后就是随机小于半径的长度，这里有个问题需要注意一下，这里并不是直接随机出一个 [0, 1] 中的小数再乘以半径r，而是要对随机出的 [0, 1] 中的小数取个平方根再乘以半径r。
 * 这是为啥呢，简单来说，是为了保证等概率。
 * 如果不用平方根的话，那么表示圆的时候 (len * cos(theta)) ^ 2 + (len * sin(theta) ^ 2，这里就相当于对随机出的 [0, 1] 中的小数平方了，那么其就不是等概率的了，因为两个小于1的小数相乘了，其会更加靠近0，这就是为啥要平方一下的原因。最后在求点位置的时候要加上圆心的偏移即可.
 */
public class DistributionFunction {

    double rad, xc, yc;
    Random rand = new Random();

    /**
     *
     * @param radius 半径
     * @param x_center x轴
     * @param y_center  y轴
     */
    public DistributionFunction(double radius, double x_center, double y_center)
    {
        rad = radius;
        xc = x_center;
        yc = y_center;
    }
    public double[] randPoint()
    {
        // 随机小于半径的长度.
        double d = rad * Math.sqrt(rand.nextDouble());
        //一圈是 360 度，即 2pi，所以随机出一个 [0, 1] 中的小数，再乘以 2pi
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
