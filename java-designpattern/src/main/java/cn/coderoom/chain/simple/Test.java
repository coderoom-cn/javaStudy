package cn.coderoom.chain.simple;

public class Test {

    public static void main(String[] args) {

        LinkedHandler handler = new LinkedHandler();
        LinkedHandler handler0 = new LinkedHandler();
        LinkedHandler handler1 = new LinkedHandler();
        LinkedHandler handler2 = new LinkedHandler();

        handler.setNextHandle(handler0);
        handler0.setNextHandle(handler1);
        handler1.setNextHandle(handler2);

        handler.handleRequest("");
        /*handler0.handleRequest("");
        handler1.handleRequest("");
        handler2.handleRequest("");*/



    }

}
