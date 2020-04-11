package cn.coderoom.makebugs.basic.grammar;

import java.util.ArrayList;
import java.util.List;

/**
 * 需求：模拟for bug
 * 前置条件：设置JVM参数设置最大内存为256M(VM option : -Xmx256m )，以便模拟出OOM。<br/>
 * @class ForDemo
 * @package cn.coderoom.makebugs.basic.grammar
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/4/2 11:06 
*/ 
public class ForDemo {

    public void testFor00(){
        List<User> list = new ArrayList<User>();
        while(true){
            User u = new User();
            u.setAge(1);
            u.setName("zl");
            //list.add(u);
            //System.out.println(list.size());
        }
    }

    public void testFor01(){
        //List<User> list = new ArrayList<User>();
        User u = null;
        while(true){
            u = new User();
            u.setAge(1);
            u.setName("zl");
            //list.add(u);
            //System.out.println(list.size());
        }
    }

    static class User{
        private long age;
        private String name;
        public long getAge() {
            return age;
        }
        public void setAge(long age) {
            this.age = age;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }


    }


}
