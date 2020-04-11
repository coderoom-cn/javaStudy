package cn.coderoom.books.effectivejava.chapter1.five;


/**
 * 基本类型和装箱基本类型效率相差.
 * 优先使用基本类型
 * @package: cn.coderoom.books.effectivejava.chapter1.five
 * @class: AutoBoxing
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 */
public class AutoBoxing {

    public static void baseType(){
        long start = System.currentTimeMillis();
        long sum = 0L;
        for (long i=0 ; i<=Integer.MAX_VALUE ;i++){
            sum += i;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("total time is "+ (end-start));
    }

    public static void boxingType(){
        long start = System.currentTimeMillis();
        Long sum = 0L;
        for (long i=0 ; i<=Integer.MAX_VALUE ;i++){
            sum += i;
        }
        System.out.println(sum);
        long end = System.currentTimeMillis();
        System.out.println("total time is "+ (end-start));
    }

    public static void main(String[] args){
        //baseType();
        boxingType();
    }
}
