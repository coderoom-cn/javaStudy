
# JMH
简介：JMH is a Java harness for building, running, and analysing nano/micro/milli/macro benchmarks written in Java and other languages targetting the JVM.
JMH(Java Microbenchmark Harness)是用于代码微基准测试的工具套件，主要是基于方法层面的基准测试，精度可以达到纳秒级。该工具是由 Oracle 内部实现 JIT 的大牛们编写的，他们应该比任何人都了解 JIT 以及 JVM 对于基准测试的影响。JMH不止能对Java语言做基准测试，还能对运行在JVM上的其他语言做基准测试。

当你定位到热点方法，希望进一步优化方法性能的时候，就可以使用 JMH 对优化的结果进行量化的分析。
JMH 比较典型的应用场景如下：
1.想准确地知道某个方法需要执行多长时间，以及执行时间和输入之间的相关性。
2。对比接口不同实现在给定条件下的吞吐量。
3.查看多少百分比的请求在多长时间内完成。

## 推荐用法
官方推荐创建一个独立的Maven工程来运行JMH基准测试，这样更能确保结果的准确性。当然也可以在已存在的工程中，或者在IDE上运行，但是越复杂，结果越不可靠（more complex and the results are less reliable）。

## 简单实用
推荐用法通过命令行创建，构建和运行JMH基准测试。

### JMH 可视化#
如果想将测试结果以图表的形式可视化，可以试下这些网站：
JMH Visual Chart：http://deepoove.com/jmh-visual-chart/
JMH Visualizer：https://jmh.morethan.io/

## 引用
https://www.cnblogs.com/54chensongxia/p/15485421.html
http://code2life.top/2019/05/11/0043-jmh-benchmark/
http://tutorials.jenkov.com/java-performance/jmh.html
https://xie.infoq.cn/article/9d8c81113a978540cc5793139

## 结果示例
Benchmark                               (length)  Mode  Cnt     Score     Error  Units
StringConnectTest.testStringAdd               10  avgt    5   161.496 ±  17.097  ns/op
StringConnectTest.testStringAdd               50  avgt    5  1854.657 ± 227.902  ns/op
StringConnectTest.testStringAdd              100  avgt    5  6490.062 ± 327.626  ns/op
StringConnectTest.testStringBuilderAdd        10  avgt    5    68.769 ±   4.460  ns/op
StringConnectTest.testStringBuilderAdd        50  avgt    5   413.021 ±  30.950  ns/op
StringConnectTest.testStringBuilderAdd       100  avgt    5   819.329 ±  72.698  ns/op
在进行 5 次迭代后，进行统计，在本例中，length 为 100 的情况下 testStringBuilderAdd 方法的平均执行花费时间为 819.329 ns，误差为 72.698 ns。



