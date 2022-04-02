package cn.coderoom.template.simple;

public class Test {

    public static void main(String[] args) {
        System.out.println("=========Java课程start=========");
        JavaCourse java = new JavaCourse();
        java.createPPT();
        java.liveVideo();
        System.out.println("=========Python课程start=========");
        PythonCourse python = new PythonCourse();

    }

}
