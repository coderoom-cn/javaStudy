package cn.coderoom.utils.jvm;

import java.lang.management.ManagementFactory;

/**
 * @package：cn.coderoom.utils.jvm
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/1/8
 */
public class PIDTool {

    public static String getPid(){
        String name = ManagementFactory.getRuntimeMXBean().getName();
        System.out.println(name);
        String pid = name.split("@")[0];
        System.out.println("Pid is:" + pid);

        return pid;
    }
}
