package cn.coderoom.chain;

public class LinkedHandlerC extends LinkedHandler {

    @Override
    public void handleRequest(String request) {

        if (request.equals("one")) {
            System.out.println("具体处理者1负责处理该请求！");
        } else {
            if (getNextHandler() != null) {
                System.out.println("具体处理者LinkedHandlerC负责处理该请求！-----------");
                getNextHandler().handleRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }

    }

}
