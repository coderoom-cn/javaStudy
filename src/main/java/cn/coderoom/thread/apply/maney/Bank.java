package cn.coderoom.thread.apply.maney;

import java.util.Objects;

/**
 * 程序分析：
 *         钱的数量要设置成一个静态的变量，两个人要取的同一个对象值。
 * @package: cn.coderoom.thread.apply.maney
 * @class: Bank
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2020/4/21 21:21
 */
public class Bank {

    // 假设一个账户有1000块钱
    static double money = 1000;
    // 柜台Counter取钱的方法
    private void Counter(double money) {
        Bank.money -= money;
        System.out.println("柜台取钱" + money + "元，还剩" + Bank.money + "元！");
    }
    // ATM取钱的方法
    private void ATM(double money) {
        Bank.money -= money;
        System.out.println("ATM取钱" + money + "元，还剩" + Bank.money + "元！");
    }

    //提供一个对外取款途径，防止直接调取方法同时取款时，并发余额显示错误
    public synchronized void outMoney(double money, String mode) throws Exception{
        if(money > Bank.money){
            //校验余额是否充足
            throw new Exception("取款金额"+money+",余额只剩"+Bank.money+"，取款失败");
        }
        if(Objects.equals(mode, "ATM")){
            ATM(money);
        } else {
            Counter(money);
        }
    }

}
