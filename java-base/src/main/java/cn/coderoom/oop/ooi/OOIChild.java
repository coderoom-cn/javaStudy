package cn.coderoom.oop.ooi;

/**
 * 不能违反里氏替换原则
 */
public class OOIChild extends OOIFather {

    private Integer i;

    public OOIChild(){
        i = Integer.valueOf(1);
//        super.start();
        System.out.println("========== this is child's construct");
        //this.start();
        //super.start();
    }

    @Override
    public void start(){
        System.out.println(i.byteValue());
        System.out.println("========== this is child's start");

    }

    public void ca(){
        System.out.println(i.byteValue());
    }
}
