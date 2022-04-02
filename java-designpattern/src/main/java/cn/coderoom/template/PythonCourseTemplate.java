package cn.coderoom.template;

public class PythonCourseTemplate extends AbstractCourseTemplate {

    private boolean needCheckHomework = false;
    
    public void setNeedCheckHomework(boolean needCheckHomework) {
        this.needCheckHomework = needCheckHomework;
    }

    @Override
    protected boolean needCheckHomework() {
        return this.needCheckHomework;
    }

    @Override
    protected void checkHomework() {
        System.out.println("检查Python作业");
    }

}
