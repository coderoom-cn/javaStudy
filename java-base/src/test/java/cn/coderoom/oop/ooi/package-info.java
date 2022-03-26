package cn.coderoom.oop.ooi;

/**
 *
 * 为什么要有接口？
 * 因为接口可以避免类继承的所有问题。
 * 再说的严谨一点：JAVA语言中对接口的限制可以避免因类继承而引起的所有问题。
 *
 * **/
/**
 *
 * 如果确实是is a的关系，才使用继承，否则建议使用组合关系（has-a）。
 * 他们还给这种方式起了个名字：装饰器模式。这个模式在JDK的IO包中体现的淋漓尽致。
 * 例如，InputStream 的子类 FilterInputStream，OutputStream 的子类 FilterOutputStream，Reader 的子类 BufferedReader 以及 FilterReader，还有 Writer 的子类 BufferedWriter、FilterWriter 以及 PrintWriter 等，它们都是抽象装饰类。
 * **/
/**
 * 装饰器模式
 * 通常情况下，扩展一个类的功能会使用继承方式来实现。
 * 但继承具有静态特征，耦合度高，并且随着扩展功能的增多，子类会很膨胀。
 * 如果使用组合关系来创建一个包装对象（即装饰对象）来包裹真实对象，并在保持真实对象的类结构不变的前提下，为其提供额外的功能，这就是装饰器模式的目标。
 * http://c.biancheng.net/view/1366.html
 *
 * **/
