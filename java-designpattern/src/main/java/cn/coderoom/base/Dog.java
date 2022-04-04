package cn.coderoom.base;

public class Dog extends Animal {

    //狗叫
    @Override
    public void voice(){
        System.out.println("------------------ dog");
    }

    @Override
    protected void move() {
        System.out.println("------------------ dog run ");
    }

}
