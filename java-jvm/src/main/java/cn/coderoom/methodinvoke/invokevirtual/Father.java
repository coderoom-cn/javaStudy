package cn.coderoom.methodinvoke.invokevirtual;

/**
 *  1、invokevirtual指令,2、动态分派，3、类加载机制
 */
public class Father {

    public void say() {
        System.out.println("i am fater");
        System.out.println(this);
        this.hello();
        /**
         * 当JVM在使用invokevirtual指令时，必须要在运行时，根据调用对象的实际值去调用对应的方法，跟invokespecial指令是不一样的。
         *  所以，在调用 this.hi() 方法时，运行时查找到 this 对象类型为 Son，而且 Son 类中存在对应的hi方法，访问权限校验也通过，所以这里调用的是 Son 类的hi方法，程序的输出为: "son say hi"
         */
        this.hi();
    }

    private void hello() {
        System.out.println("father say hello");
    }

    public void hi() {
        System.out.println("father say hi");
    }

}
