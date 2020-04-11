package cn.coderoom.juc.apply;

import java.util.concurrent.locks.LockSupport;

/**
 *
 * 方案：使用LockSupport(直接等待和恢复) <br/>
 * 使用Lock锁的方式有个问题，我使用了sleep 让打印字符的线程等待了100毫秒，我没有找到合适的方式控制两个同时运行的线程的顺序，如果你有什么好方法希望也能分享出来。
 * 要想不断提高自己就去面试吧，即使你不想换工作，在面试中确实能发现自己的不足和薄弱的地方。
 *
 * @package：cn.coderoom.juc.apply
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class PrintNumAndChar01 {

    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        char[] chars = { 'a', 'b', 'c', 'd', 'e' };
        PrintNums t1 = new PrintNums(nums);
        PrintChars t2 = new PrintChars(chars);
        t1.setPrintChars(t2);
        t2.setPrintNums(t1);
        t1.start();
        t2.start();

    }

    public static class PrintNums extends Thread {
        private int[] nums;
        private PrintChars printChars;

        public PrintNums(int[] a1) {
            super();
            this.nums = a1;
        }

        public void setPrintChars(PrintChars printChars) {
            this.printChars = printChars;
        }

        @Override
        public void run() {
            int count = 0;
            for (int i = 0; i < nums.length; i++) {
                if(count==2){
                    count = 0;
                    LockSupport.unpark(printChars);
                    LockSupport.park();
                }
                System.out.print(nums[i]);
                count++;
            }
            LockSupport.unpark(printChars);
        }
    }

    public static class PrintChars extends Thread {
        private char[] chars;
        private PrintNums printNums;

        public PrintChars(char[] chars) {
            super();
            this.chars = chars;
        }

        public void setPrintNums(PrintNums printNums) {
            this.printNums = printNums;
        }

        @Override
        public void run() {
            LockSupport.park();
            int count = 0;
            for (int i = 0; i < chars.length; i++) {
                if(count==1){
                    count = 0;
                    LockSupport.unpark(printNums);
                    LockSupport.park();
                }
                System.out.print(chars[i]);
                count++;
            }
            LockSupport.unpark(printNums);
        }
    }

}
