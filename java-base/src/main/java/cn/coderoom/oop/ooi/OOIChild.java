package cn.coderoom.oop.ooi;

/**
 * 不能违反里氏替换原则
 */
public class OOIChild extends OOIFather {

    private Integer i;
    public OOIChild(){
        i = 1;
//        super.start();
        this.start();
    }

    @Override
    public void start(){
        System.out.println(i.byteValue());
    }
}
