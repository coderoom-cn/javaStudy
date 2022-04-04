package cn.coderoom.chain.simple;

public class LinkedHandler {

    private LinkedHandler next;

    public void setNextHandle(LinkedHandler next) {
        this.next = next;
    }

    public LinkedHandler getNextHandle() {
        return next;
    }

    //处理请求的方法
    public void handleRequest(String request){
        if (request.equals("one")) {
            System.out.println("具体处理者1负责处理该请求！");
        } else {
            if (getNextHandle() != null) {
                getNextHandle().handleRequest(request);
            } else {
                System.out.println("没有人处理该请求！");
            }
        }
    }

}
