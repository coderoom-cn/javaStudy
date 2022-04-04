package cn.coderoom.base;

public class Wolf extends Animal {

    @Override
    public void voice() {
        // super.voice();
        System.out.println("------------- wolf voice ");
    }

    @Override
    protected void move() {
        System.out.println("------------- wolf run");
    }
}
