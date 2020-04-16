package cn.coderoom.jvm.object;

/**
 *
 * 64位开启指针压缩的话，markword变成8字节，压缩了class指针为4字节，故对象头12字节
 * 64位没有开启指针压缩的话，markword8字节，class指针8字节，对象头16字节
 * 32位markword为4字节，class指针为4字节，对象头8字节
 *
 * 另外，静态属性所占用的空间通常不算在对象本身，因为它的引用是在方法区。
 *
 * @package：cn.coderoom.jvm.object
 * @author: Leesire
 * @email:coderoom.cn@gmail.com
 */
public class ObjectSize {


}
