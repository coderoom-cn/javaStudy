package cn.coderoom.juc.containers;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 *
 * @package：cn.coderoom.juc.containers
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class TestQueue {

    public static void main(String[] args) {
        Queue<Integer> q = new ArrayBlockingQueue<>(2);
        q.add(0);
        q.add(1);
        q.add(2);
        q.add(3);
        System.out.println(q);


    }

}
