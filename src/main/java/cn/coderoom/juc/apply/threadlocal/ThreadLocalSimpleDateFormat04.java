package cn.coderoom.juc.apply.threadlocal;

/** 
 * 在这个代码中我们可以看出，
 * 我们有一个UserContextHolder，
 * 里面保存了一个ThreadLocal，
 * 在调用Service1的方法的时候，
 * 就往里面存入了user对象，
 * 而在后面去调用的时候，
 * 直接从里面用 get 方法取出来就可以了。
 * 没有参数层层传递的过程，非常的优雅、方便。
 *
 * @class ThreadLocalSimpleDateFormat04 
 * @package cn.coderoom.juc.apply.threadlocal
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2019/4/26 14:11
*/ 
public class ThreadLocalSimpleDateFormat04 {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            int fin = i;
            new Thread(()-> new Service1().service1l(fin+"")).start();
        }
    }

}
class Service1{
    public void service1l(String str){
        User user = new User(str);
        UserContextHolder.holder.set(user);// 放入
        new Service2().service2();
    }
}

class Service2{
    public void service2(){
        User user = UserContextHolder.holder.get();  // 取出
        System.out.println(Thread.currentThread().getName()+"  Service2拿到用户名： "+user.name);
        new Service3().service3();
    }
}

class Service3{
    public void service3(){
        User user = UserContextHolder.holder.get();  // 取出
        System.out.println(Thread.currentThread().getName()+"  Service3拿到用户名： "+user.name);
        UserContextHolder.holder.remove();
    }
}

class UserContextHolder{
    public static ThreadLocal<User> holder = new ThreadLocal<>();
}

class User{
    String name;
    public User(String name) {
        this.name = name;
    }
}
