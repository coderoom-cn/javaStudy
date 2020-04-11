package cn.coderoom.jvm.tools;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *  VisualVM:JConsole的升级版本jvisualvm,VisualVM在功能上比JConsole提升很多.
 * @class JvisualvmTool
 * @package cn.coderoom.jvm.tools
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/8 13:16
*/
public class JvisualvmTool {

    /**
     *  借助java.lang.ProcessBuilder打开
     * @param
     * @author lim
     * @date 2020/1/8 13:12
     * @return void
    */
    public void useProcessBuilder() throws IOException {
        List<String> commands = new ArrayList<String>();
        commands.add("D:\\Java\\jdk1.8\\bin\\jvisualvm.exe");
        new ProcessBuilder(commands).start();
    }

}
