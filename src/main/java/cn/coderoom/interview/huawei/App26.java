package cn.coderoom.interview.huawei;

import java.util.LinkedList;

/**
 * 栈的贪心算法
 *
 * 字符串n，删除k位，剩下字符串最小
 */
public class App26 {


    public static String removeKdigits(String num, int k) {
        LinkedList<Character> stack = new LinkedList();

        // 该数字小于栈顶部，即该数字的左侧数字，那么该数字的左侧数字弹出堆栈，即删除左邻居。
        for(char digit : num.toCharArray()) {
            while(stack.size() > 0 && k > 0 && stack.peekLast() > digit) {
                stack.removeLast();
                k -= 1;
            }
            stack.addLast(digit);
        }

        // 删除其余数字
        for(int i=0; i<k; ++i) {
            stack.removeLast();
        }

        // 构建最终字符串，同时删除前导零
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        for(char digit: stack) {
            if(leadingZero && digit == '0') continue;
            leadingZero = false;
            ret.append(digit);
        }

        if (ret.length() == 0) return "0";
        return ret.toString();
    }


}
