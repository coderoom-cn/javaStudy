package cn.coderoom.oop.ooi;

/**
 * 不能违反里氏替换原则
 */
public class OOIChildb extends OOIFather {

    private Integer i;

    public OOIChildb(){
        System.out.println("========== this is OOIChildb's construct");
    }

    @Override
    public void start(){
        System.out.println("---------------- This is OOIChilda's start ");
        super.starta();
        System.out.println(i.byteValue());
    }

    public void caa(){
        System.out.println(i.byteValue());
    }
}
