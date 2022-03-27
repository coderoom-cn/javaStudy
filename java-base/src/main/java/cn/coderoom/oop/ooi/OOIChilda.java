package cn.coderoom.oop.ooi;

/**
 * 不能违反里氏替换原则
 */
public class OOIChilda extends OOIFather {

    private Integer i;

    public OOIChilda(){
        i = Integer.valueOf(1);
//        super.start();
        System.out.println("========== this is childa's construct");
        this.start();
    }

    @Override
    public void start(){
        System.out.println("---------------- This is OOIChilda's start ");
        System.out.println(i.byteValue());
    }

    public void caa(){
        System.out.println(i.byteValue());
    }
}
