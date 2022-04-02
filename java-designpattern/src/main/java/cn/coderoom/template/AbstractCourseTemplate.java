package cn.coderoom.template;

/**
 * 思考为什么用抽象类，不用接口
 */
public abstract class AbstractCourseTemplate {

    /**
     *  createCourse() 是模板方法，它定义了网课业务的基本流程。postPreResource()、createPPT()、liveVideo()、postNote()、postResource()、postHomework()
     *  这 6 个步骤在所有课程中都是固定的，只有 checkHomework() 方法在所有课程中都不一样，所以声明为抽象方法，由子类自行实现。
     */
    public final void createCourse() {
        //1、发布预习资料
        postPreResource();
        //2、制作课件
        createPPT();
        //3、在线直播
        liveVideo();
        //4、提交课堂笔记
        postNote();
        //5、提交源码
        postResource();
        //6、布置作业
        postHomework();
        //7、检查作业
        if (needCheckHomework()) {
            checkHomework();
        }
    }
    protected abstract void checkHomework();

    //钩子方法

    /**
     * 钩子方法 needCheckHomework 返回一个 boolean 类型的值，控制是否需要检查作业。
     * 设计钩子方法的主要目的是干预执行流程，使得控制执行流程更加灵活，更符合实际业务的需求。钩子方法的返回值一般为条件分支语句的返回值，可以根据自己的业务场景决定是否需要使用钩子方法。
     * @return
     */
    protected boolean needCheckHomework() {
        return false;
    }

    protected void postHomework() {
        System.out.println("布置作业");
    }
    protected void postNote() {
        System.out.println("提交课程笔记");
    }
    protected void postResource() {
        System.out.println("提交源码");
    }
    protected void liveVideo() {
        System.out.println("在线直播");
    }
    protected void createPPT() {
        System.out.println("制作课件");
    }
    protected void postPreResource() {
        System.out.println("发布预习资料");
    }

}
