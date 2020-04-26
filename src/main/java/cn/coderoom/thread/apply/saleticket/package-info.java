package cn.coderoom.thread.apply.saleticket;
/**
 * 三个售票窗口同时出售20张票
 *
 * 程序分析：
 *     (1)票数要使用同一个静态值（共享对象）
 *     (2)为保证不会出现卖出同一个票数，要java多线程同步锁。
 * 设计思路：
 *     (1)创建一个站台类Station，继承Thread，重写run方法，在run方法里面执行售票操作！售票要使用同步锁：即有一个站台卖这张票时，其他站台要等这张票卖完！
 *     (2)创建主方法调用类
 *
 * @package cn.coderoom.thread.apply
 * @author lim
 * @email coderoom.cn@gmail.com
 * @date 2020/1/10 16:29
 */