package cn.coderoom.oop.ooi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 子类的实现可能会影响父类（可能违反里氏替换原则）
 */
public class OOIChildTest {

    @Test
    public void start(){
//        new OOIChild();
        //new OOIFather().start();
//        new OOIFather();
        /**
         * f引用可以用所有父类中的方法，无法调用子类特有的方法。
         * 当子类重写了父类的方法时，引用f调用的就是子类中重写的方法，而不是父类的方法。
         * 因为f仅仅是一个引用而不是对象，调用类的属性和方法其实实质是通过类的实例进行调用的，f是父类的引用而真正的实例是子类的的实例，所以f无法调用子类特有的方法。
         * 继承的时候子类中继承了类中可以继承的所有方法和属性（特殊修饰除外），如果子类重写了父类的方法，子类的实例肯定调用的是子类中重写的方法。
         *
         * 因为运行时的类型是子类，因而调用的是子类中重写的方法
         */
        new OOIChild();
        new OOIFather();
        OOIFather f = new OOIChild();

    }

    @Test
    public void starta(){

        OOIFather f = new OOIChild();
        /**
         * 编译时指向父类中的start()，在运行时由于是invokevirtual调用，因此 引用变量f 将变成实际类型Son，如果OOIChild中有start()，就调用Son自己的，若没有就调用父类的
         *
         */
        f.start();
        OOIFather fa = new OOIChildb();
        fa.startb();
        fa.start();
    }

}