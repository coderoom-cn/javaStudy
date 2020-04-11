package cn.coderoom.jvm.reference;

import java.io.IOException;

/**
 *
 * 强引用
 *
 * @package：cn.coderoom.jvm.reference
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class NormalReference {

    public static void main(String[] args) throws IOException {
        M m = new M();
        m = null;
        System.gc(); //DisableExplicitGC

        System.in.read();
    }

}
