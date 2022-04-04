package cn.coderoom.chain;

public class Test {

    public static void main(String[] args) {

        LinkedHandler handlerA = new LinkedHandlerA();
        LinkedHandler handlerB = new LinkedHandlerB();
        LinkedHandler handlerC = new LinkedHandlerC();

        handlerA.setNextHandler(handlerB);
        handlerB.setNextHandler(handlerC);
        handlerA.handleRequest("");

    }

}
