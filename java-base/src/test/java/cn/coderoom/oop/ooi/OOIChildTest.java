package cn.coderoom.oop.ooi;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 子类的实现可能会影响父类（可能违反里氏替换原则）
 */
public class OOIChildTest {

    @Test
    public void start(){
        new OOIChild();
//        new OOIFather();
    }
}