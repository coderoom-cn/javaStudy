package cn.coderoom.juc.apply;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 有N张火车票，每张票都有一个编号
 * 同时有10个窗口对外售票
 * 请写一个模拟程序
 *
 * 分析下面的程序可能会产生哪些问题？
 * 重复销售？超量销售？
 *
 * @package：cn.coderoom.juc.apply
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class TicketSeller1 {

    static List<String> tickets = new ArrayList<>();

    static {
        for(int i=0; i<1000; i++) {
            tickets.add("票编号：" + i);
        }
    }



    public static void main(String[] args) {
        for(int i=0; i<10; i++) {
            // 创建10个线程
            new Thread(()->{

                while(tickets.size() > 0) {
                    System.out.println("销售了--" + tickets.remove(0));
                }

            }).start();
        }
    }

}
