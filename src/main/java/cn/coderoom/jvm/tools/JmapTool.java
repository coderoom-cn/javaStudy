package cn.coderoom.jvm.tools;

import cn.coderoom.utils.jvm.CmdTool;
import cn.coderoom.utils.jvm.PIDTool;

import java.io.IOException;

/**
 * jmap(Java Memory Map)，是jdk自带的jvm内存分析的工具，倾向于分析jvm内存中对象信息。
 * @class JmapTool
 * @package cn.coderoom.jvm.tools
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/8 14:03
*/
public class JmapTool {


    public void startJmap() throws IOException {
        String pid = PIDTool.getPid();
        String cmdStr = "D:\\Java\\jdk1.8\\bin\\jmap -heap "+pid;
        String result = CmdTool.executeCmd(cmdStr);
        System.out.println(result);
    }

}
