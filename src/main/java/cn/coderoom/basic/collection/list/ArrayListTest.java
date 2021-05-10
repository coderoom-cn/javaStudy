package cn.coderoom.basic.collection.list;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * ArrayList 是一个 **数组队列** ，相当于 动态数组的数据结构。与Java中的数组相比，它的容量能动态增长。
 * 它继承于AbstractList，实现了List, RandomAccess, Cloneable, java.io.Serializable这些接口。
 *
 * 随机访问get和set，ArrayList觉得优于LinkedList，因为LinkedList要移动指针。
 *
 * @package: cn.coderoom.basic.collection.list
 * @class: ArrayListTest
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 * @version: 1.0
 * @date: 2019/4/19 21:44
 */
public class ArrayListTest {

    /**
     * List a 与 ArrayList b 声明的区别
     */
    public void test0(){
        // 依赖倒置原则,a只可以使用List的抽象方法
        List a = new ArrayList();
        //b可以使用ArrayList特有的方法.
        ArrayList b = new ArrayList();
    }

    /**
     *  删除操作
     */
    public void remove(){
        List<ArrayListTest> a = new ArrayList();
        ArrayList<ArrayListTest> b = new ArrayList();
        ArrayListTest c = new ArrayListTest();
        //删除列表中第一个出现O的元素
        a.remove(c);
        // 删除列表中指定位置的元素
        a.remove(0);
        // 删除列表中包含C的所有元素
        a.removeAll(b);

        // TODO
        // removeIf() removeRang();
        // 清除所有的元素。返回类型为void
        a.clear();
    }

    public void update(){

        List<ArrayListTest> a = new ArrayList();
        ArrayList<ArrayListTest> b = new ArrayList();
        a.subList(0,1);
        a.toArray();

        // 修改当前实例的容量是列表的当前大小。
        b.trimToSize();
        // TODo
        // sort() 按照指定的排序规则排序
    }

    public void get(){
        List<ArrayListTest> a = new ArrayList();
        ArrayList<ArrayListTest> b = new ArrayList();
        ArrayListTest c = new ArrayListTest();

        // 如果包含元素o,则返回为true
        a.contains(c);
        // 返回此列表中指定元素的第一次出现的索引，如果列表不包含此元素，返回-1
        a.indexOf(c);
        // 返回此列表中指定元素的最后一次出现的索引，如果列表不包含此元素，返回-1
        a.lastIndexOf(c);

    }
}
