package cn.coderoom.oop.ooi;

/**
 * 继承的JVM初始化顺序：
 * 父类的静态代码块->子类的静态代码块->父类的静态变量->子类的静态变量->父类的普通初始化对象->子类的普通初始化对象->父类构造方法->子类构造方法！
 */
public class OOIFather {

    static {
        System.out.println("---------------- This is father's static field");
    }

    public OOIFather(){
        System.out.println("---------------- This is father's  construct");
        //starta();
        /**
         * 如果父类构造器调用了被子类重写的方法，且通过子类构造函数创建子类对象，调用了这个父类构造器（无论显示还是隐式），
         * 就会导致父类在构造时实际上调用的是子类覆盖的方法。
         *
         * 如果在父类构造函数中调用被子类重写的方法，会导致子类重写的方法在子类构造器的所有代码之前执行，从而导致子类重写的方法访问不到子类实例变量的值，因为此时这些变量还没有被初始化。
         * 优点：通过继承相同的父类，初始化子类时，父类会调用不同子类的不同复写方法，从而实现多态性。
         */
        /**
         * 因为运行时的类型是子类，因而调用的是子类中重写的方法,
         * 所以与是否用 this 还是 super 没关系
         */
        start();
    }

    /**
     * 因为继承关系的JVM加载机制的影响，
     * 导致子类重写的方法在子类构造器的所有代码之前执行。
     */
    protected void start(){
        System.out.println("---------------- This is father's start ");
    }

    /**
     * 没有被覆盖的方法
     */
    protected void starta(){
        System.out.println("----------------  This is father's starta ");
    }

    protected void startb(){
        System.out.println("----------------  This is father's startb ");
    }

}
