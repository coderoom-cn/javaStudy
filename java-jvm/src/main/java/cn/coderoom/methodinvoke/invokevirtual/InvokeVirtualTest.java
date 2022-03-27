package cn.coderoom.methodinvoke.invokevirtual;

/**
 *
 * 参考： http://wxweven.win/2017/09/15/JVM-invokespecial%E5%92%8Cinvokevirtual/
 */
public class InvokeVirtualTest {

    public static void main(String[] args) {
        Father test = new Son();
        test.say();
    }

}
