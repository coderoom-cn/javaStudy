package cn.coderoom.jdk.rtjar.java.util;

import java.util.Objects;

/**
 * transient: 将不需要序列化的属性前添加关键字transient，序列化对象的时候，这个属性就不会被序列化。
 *
 * @author coderoom.cn
 * @class HashMap
 * @package cn.coderoom.jdk.rtjar.java.util
 * @email coderoom.cn@gmail.com
 */
public class HashMap<K, V>{

    private static final long serialVersionUID = 362498820763181265L;

    /**
     * The default initial capacity - MUST be a power of two.
     */
    static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

    /**
     * The maximum capacity, used if a higher value is implicitly specified
     * by either of the constructors with arguments.
     * MUST be a power of two <= 1<<30.
     * 最大容量 2的30次方 （移位运算符）
     */
    static final int MAXIMUM_CAPACITY = 1 << 30;

    /**
     * The load factor used when none specified in constructor.
     * 默认的加载因子
     */
    static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * The bin count threshold for using a tree rather than list for a
     * bin.  Bins are converted to trees when adding an element to a
     * bin with at least this many nodes. The value must be greater
     * than 2 and should be at least 8 to mesh with assumptions in
     * tree removal about conversion back to plain bins upon
     * shrinkage.
     */
    static final int TREEIFY_THRESHOLD = 8;

    /**
     * The bin count threshold for untreeifying a (split) bin during a
     * resize operation. Should be less than TREEIFY_THRESHOLD, and at
     * most 6 to mesh with shrinkage detection under removal.
     */
    static final int UNTREEIFY_THRESHOLD = 6;

    /**
     * The smallest table capacity for which bins may be treeified.
     * (Otherwise the table is resized if too many nodes in a bin.)
     * Should be at least 4 * TREEIFY_THRESHOLD to avoid conflicts
     * between resizing and treeification thresholds.
     */
    static final int MIN_TREEIFY_CAPACITY = 64;

    /**
     * Basic hash bin node, used for most entries.  (See below for
     * TreeNode subclass, and in LinkedHashMap for its Entry subclass.)
     * <p>
     * 链表（单链表）的结构：Hash桶节点
     * Node是HashMap的一个内部类，实现了Map.Entry接口，本质是就是一个映射(键值对)。
     */
    static class Node<K, V> implements Map.Entry<K, V> {
        final int hash; //哈希值
        final K key; //key
        V value; //value
        Node<K, V> next; //链表后置节点

        Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public final K getKey() {
            return key;
        }

        public final V getValue() {
            return value;
        }

        public final String toString() {
            return key + "=" + value;
        }

        //每一个节点的hash值，是将key的hashCode 和 value的hashCode 进行异或运算（ ^ ） 得到的。
        public final int hashCode() {
            return Objects.hashCode(key) ^ Objects.hashCode(value);
        }

        //设置新的value 同时返回旧value
        public final V setValue(V newValue) {
            V oldValue = value;
            value = newValue;
            return oldValue;
        }

        public final boolean equals(Object o) {
            if (o == this)
                return true;
            if (o instanceof Map.Entry) {
                Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
                if (Objects.equals(key, e.getKey()) &&
                        Objects.equals(value, e.getValue()))
                    return true;
            }
            return false;
        }
    }

    /* ---------------- Static utilities -------------- */

    /**
     * 确定哈希桶数组索引位置
     * HashMap定位数组索引位置，直接决定了hash方法的离散性能。
     * Hash算法本质上就是三步：取key的hashCode值、高位运算、取模运算。
     */
    static final int hash(Object key) {
        int h;
        // h = key.hashCode() 为第一步 取hashCode值
        // h ^ (h >>> 16)  为第二步 高位参与运算
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


}

