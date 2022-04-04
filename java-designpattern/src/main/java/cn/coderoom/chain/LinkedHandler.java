package cn.coderoom.chain;

public abstract class LinkedHandler {

    private LinkedHandler handler;

    public void setNextHandler(LinkedHandler handler) {
        this.handler = handler;
    }

    public LinkedHandler getNextHandler() {
        return handler;
    }

    /**
     * 不同的实现
     * @param request
     */
    public abstract void handleRequest(String request);


}
