package cn.coderoom;

/**
 *
 * 解析 super 用法
 * super.字段
 * super()
 * super.虚方法()
 * super.getClass()
 * super.hashCode()
 *
 *  参考 ： https://blog.csdn.net/qq_44707077/article/details/118223538
 */
public class Thisandsuper {

    static class Person{
        private int anInt;
        private static int anStaticInt;

        public Person(){}

        public static Object staticTest(){
            anStaticInt = 45;
            return 0;
        }

        public Object test(){
            int i =1;
            this.anInt = 123;
            return 1;
        }

        public static void main(String[] args){

            Person person = new Person();
            person.test();
        }
    }
}
