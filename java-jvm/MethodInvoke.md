
## JVM提供的方法调用指令集

JVM提供了5种方法调用指令，其作用列举如下：

1. invokestatic：该指令用于调用静态方法，即使用 static 关键字修饰的方法；
2. invokespecial：该指令用于三种场景：调用实例构造方法，调用私有方法（即private关键字修饰的方法）和父类方法（即super关键字调用的方法）；
3. invokeinterface：该指令用于调用接口方法，在运行时再确定一个实现此接口的对象；
4. invokevirtual：该指令用于调用虚方法（就是除了上述三种情况之外的方法）；
5. invokedynamic：在运行时动态解析出调用点限定符所引用的方法之后，调用该方法；在JDK1.7中推出，主要用于支持JVM上的动态脚本语言（如Groovy，Jython等）。

