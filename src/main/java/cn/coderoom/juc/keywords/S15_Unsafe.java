package cn.coderoom.juc.keywords;

import sun.misc.Unsafe;

/**
 *
 *
 * @package：cn.coderoom.juc.keywords
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class S15_Unsafe {

    static class M {
        private M() {}

        int i =0;
    }

    public static void main(String[] args) throws InstantiationException {
        Unsafe unsafe = Unsafe.getUnsafe();
        M m = (M)unsafe.allocateInstance(M.class);
        m.i = 9;
        System.out.println(m.i);
    }

}
