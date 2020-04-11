package cn.coderoom.jvm.reference;

import java.lang.ref.WeakReference;

/**
 *
 *
 * @package：cn.coderoom.jvm.reference
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class TestWeakReference {

    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());


        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();

    }

}
