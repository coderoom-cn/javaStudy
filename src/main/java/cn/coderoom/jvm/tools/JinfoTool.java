package cn.coderoom.jvm.tools;

import cn.coderoom.utils.jvm.CmdTool;

import java.io.IOException;

/**
 * @package：cn.coderoom.jvm.tools
 * @description:
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 * @createtime: 2020/1/8
 */
public class JinfoTool {

    public void startJmap() throws IOException {
        String result = CmdTool.executeCmd("D:\\Java\\jdk1.8\\bin\\jmap -heap 4951");
        System.out.println(result);
    }

}
