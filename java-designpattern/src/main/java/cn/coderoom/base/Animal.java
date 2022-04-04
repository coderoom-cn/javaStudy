package cn.coderoom.base;

public abstract class Animal {

    //动物会发出声音
    public void voice(){
        System.out.println("----------------- animal voice");
    }

    public void behavior(){
        voice();
        move();
        System.out.println("--------------------- behavior");

    }

    protected abstract void move();

}
