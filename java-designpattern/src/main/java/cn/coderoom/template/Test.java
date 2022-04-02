package cn.coderoom.template;

public class Test {

    public static void main(String[] args) {
        System.out.println("=========Java课程start=========");
        JavaCourseTemplate java = new JavaCourseTemplate();
        java.setNeedCheckHomework(true);
        java.createCourse();
        System.out.println("=========Python课程start=========");
        PythonCourseTemplate python = new PythonCourseTemplate();
        python.setNeedCheckHomework(true);
        python.createCourse();
    }

}
