package cn.coderoom.thread.apply.maney;

/**
 * 两个人AB通过一个账户A在柜台取钱和B在ATM机取钱
 * @package: cn.coderoom.thread.apply.maney
 * @class: MainClass
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2020/4/21 21:26
 */
public class MainClass {

    public static void main(String[] args) {
        Bank bank = new Bank();
        // 实例化两个人，传入同一个银行的对象
        PersonA a = new PersonA(bank, "Counter");
        PersonB b = new PersonB(bank, "ATM");
        a.start();
        b.start();
    }

}
