package cn.coderoom.jvm.reference;

/**
 *
 * @package：cn.coderoom.jvm.reference
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class M {

    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize");
    }

}
