package cn.coderoom;

import java.lang.instrument.Instrumentation;

/**
 *
 * @package: cn.coderoom
 * @class: JavaAgentApp
 * @author: coderoom
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 */
public class JavaAgentApp
{

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("111111111111111");
        System.out.println("this is an agent.");
        System.out.println("args:" + agentArgs + "\n");
    }

}
