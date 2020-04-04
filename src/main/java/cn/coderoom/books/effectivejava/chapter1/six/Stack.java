package cn.coderoom.books.effectivejava.chapter1.six;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 *
 * 第六条：消除过期的对象引用
 * 没有及时销毁对象是OOM发生的原因之一。
 *
 * 文中原类名为：Stack
 *
 * 集合类为什么容易出现OOM：因为集合类需要自己管理内存，
 * 对于引用的对象非原子性(对象嵌套)一定要注意内存的释放。
 *
 * 延伸：同理，对非原子性的对象，在多线程中操作这些对象的时候，一定要注意死锁等问题。
 *
 * @package: cn.coderoom.books.effectivejava.chapter1
 * @class: Stack
 * @author: leesire
 * @email: coderoom.cm@gmail.com
 */
public class Stack {

    private Object[] elements;
    private int size = 0;
    private static final int DEAFULT_INITIAL_CAPACITY = 16;

    public Stack(){
        elements = new Object[DEAFULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        ensureCapacity();
        elements[size++] = e;
    }

    /**
     * 对于 栈 而言，pop后没有释放内存，会造成OOM
     * @return
     */
    public Object pop() {

        if(size == 0 )
            throw new EmptyStackException();

        return elements[--size];
    }

    /**
     * 修改后的pop()
     * @return
     */
    public Object popUpdata() {

        if(size == 0 )
            throw new EmptyStackException();

        Object e = elements[--size];
        // 清理过期引用， 主动释放内存(会在下次MinorGC 后释放)，尤其对于集合的操作一定要及时清理。
        elements[size] = null;

        return e;
    }

    /**
     * ensure space for at least one more element ，roughly
     * doubling the capacity each time the array needs to grow
     * 确保至少还有一个元素的空间，每次array需要扩大时，容量大约是原来的两倍。
     */
    private void ensureCapacity(){
        if(elements.length == size)
            elements = Arrays.copyOf(elements, 2 * size + 1);
    }


}
