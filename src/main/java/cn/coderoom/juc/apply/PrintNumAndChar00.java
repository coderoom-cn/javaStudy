package cn.coderoom.juc.apply;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 *
 * 需求：数组A内容为 1,2,3,4...52 ,数组B内容为26个英文字母，使用两个线程分别输入两个数组，打印内容为：12a34b56c78e....... 这样的规律。<br/>
 * 方案：使用自旋锁。<br/>
 *
 * @package：cn.coderoom.juc.apply
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class PrintNumAndChar00 {

    public static void main(String[] args) {

        /**
         * 自旋锁标记
        */
        AtomicBoolean spinLockFlag = new AtomicBoolean(true);

        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        char[] chars = { 'a', 'b', 'c', 'd', 'e' };
        new PrintNums(nums, spinLockFlag).start();
        new PrintChars(chars, spinLockFlag).start();

    }

    public static class PrintNums extends Thread {
        private int[] nums;
        private AtomicBoolean spinLockFlag;

        public PrintNums(int[] a1, AtomicBoolean spinLockFlag) {
            this.nums = a1;
            this.spinLockFlag = spinLockFlag;
        }

        @Override
        public void run() {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {

                /**
                 * 实现自旋
                 * 觉得空循环耗CPU，方法体里加上Thread.yield()。
                */
                while (!spinLockFlag.get()) {
                    Thread.yield();
                }

                System.out.print(nums[i]);
                count++;
                if (count == 2) {
                    spinLockFlag.set(false);
                    count = 0;
                }
            }
            spinLockFlag.set(false);
        }
    }

    public static class PrintChars extends Thread {
        private char[] chars;
        private AtomicBoolean spinLockFlag;

        public PrintChars(char[] a2, AtomicBoolean spinLockFlag) {
            this.chars = a2;
            this.spinLockFlag = spinLockFlag;
        }

        @Override
        public void run() {
            int count = 0;
            for (int i = 0; i < chars.length; i++) {
                while (spinLockFlag.get()) {
                    Thread.yield();
                }
                System.out.print(chars[i]);
                count++;
                if (count == 1) {
                    spinLockFlag.set(true);
                    count = 0;
                }
            }
            spinLockFlag.set(true);
        }
    }

}
