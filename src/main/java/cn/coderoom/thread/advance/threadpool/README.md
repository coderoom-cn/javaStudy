
#线程池

##为什么要有线程池

###new Thread的弊端
1. 每次new Thread新建对象性能差，消耗资源。
2. 线程缺乏统一管理，可能无限制新建线程，相互之间竞争，及可能占用过多系统资源导致死机或oom。
3. 缺乏更多功能，如定时执行、定期执行、线程中断。

###线程池的好处
1. 重用存在的线程，减少对象创建、消亡的开销，性能佳。
2. 可有效控制最大并发线程数，提高系统资源的使用率，同时避免过多资源竞争，避免堵塞。
3. 提供定时执行、定期执行、单线程、并发数控制等功能。

##java内置线程池
Java通过Executors提供四种线程池，分别为：

1. newCachedThreadPool： 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活**回收**空闲线程，若无可回收，则新建线程。
2. newFixedThreadPool： 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中**等待**。
3. newScheduledThreadPool： 创建一个定长线程池，支持**定时及周期性**任务执行。
4. newSingleThreadExecutor： 创建一个**单线程化**的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

###CachedThreadPool 
是一个会根据需要创建新线程的线程池。
CachedThreadPool 的 corePoolSize 被设置为空（0），maximumPoolSize 被设置为 Integer.MAX.VALUE，即它是无界的，这也就意味着如果主线程提交任务的速度高于 maximumPool 中线程处理任务的速度时，CachedThreadPool 会不断创建新的线程。极端情况下，这样会导致耗尽 cpu 和内存资源。

1. 它是一个可以无限扩大的线程池；它比较适合处理执**行时间比较小**的任务；
2. corePoolSize为0，maximumPoolSize为无限大，意味着线程数量可以无限大；
3. keepAliveTime为60S，意味着线程空闲时间超过60S就会被杀死；
4. 采用SynchronousQueue装等待的任务，这个阻塞队列没有存储空间，这意味着只要有请求到来，就必须要找到一条工作线程处理他，如果当前没有空闲的线程，那么就会再创建一条新的线程。


###FixedThreadPool
FixedThreadPool 被称为可重用固定线程数的线程池。

它是一种固定大小的线程池；
corePoolSize和maximunPoolSize都为用户设定的线程数量nThreads；
keepAliveTime为0，意味着一旦有多余的空闲线程，就会被立即停止掉；
但这里keepAliveTime无效；
阻塞队列采用了LinkedBlockingQueue，它是一个无界队列；
由于阻塞队列是一个无界队列，因此永远不可能拒绝任务；
由于采用了无界队列，实际线程数量将永远维持在nThreads，因此maximumPoolSize和keepAliveTime将无效。

####参数说明
1. 如果当前运行的线程数小于 corePoolSize， 如果再来新任务的话，就创建新的线程来执行任务；
2. 当前运行的线程数等于 corePoolSize 后， 如果再来新任务的话，会将任务加入 LinkedBlockingQueue；
3. 线程池中的线程执行完 手头的任务后，会在循环中反复从 LinkedBlockingQueue 中获取任务来执行；
####为什么不推荐使用FixedThreadPool
FixedThreadPool 使用无界队列 LinkedBlockingQueue（队列的容量为 Intger.MAX_VALUE）作为线程池的工作队列会对线程池带来如下影响 ：

1. 当线程池中的线程数达到 corePoolSize 后，新任务将在无界队列中等待，因此线程池中的线程数不会超过 corePoolSize；
2. 由于使用无界队列时 maximumPoolSize 将是一个无效参数，因为不可能存在任务队列满的情况。所以，通过创建 FixedThreadPool的源码可以看出创建的 FixedThreadPool 的 corePoolSize 和 maximumPoolSize 被设置为同一个值。
3. 由于 1 和 2，使用无界队列时 keepAliveTime 将是一个无效参数；
4. 运行中的 FixedThreadPool（未执行 shutdown()或 shutdownNow()）不会拒绝任务，在任务比较多的时候会导致 OOM（内存溢出）。

###ScheduledThreadPool
主要用来在给定的延迟后运行任务，或者定期执行任务。 这个在实际项目中基本不会被用到，所以对这部分大家只需要简单了解一下它的思想。
SpringBoot中使用@Scheduled实现定时任务。

ScheduledThreadPoolExecutor 使用的任务队列 DelayQueue 封装了一个 PriorityQueue，PriorityQueue 会对队列中的任务进行排序，
执行所需时间短的放在前面先被执行(ScheduledFutureTask 的 time 变量小的先执行)，如果执行所需时间相同则先提交的任务将被先执行(ScheduledFutureTask 的 squenceNumber 变量小的先执行)。

####ScheduledThreadPool和 Timer 的比较
1. Timer 对系统时钟的变化敏感，ScheduledThreadPoolExecutor不是；
2. Timer 只有一个执行线程，因此长时间运行的任务可以延迟其他任务。 ScheduledThreadPoolExecutor 可以配置任意数量的线程。 此外，如果你想（通过提供 ThreadFactory），你可以完全控制创建的线程;
3. 在TimerTask 中抛出的运行时异常会杀死一个线程，从而导致 Timer 死机:-( ...即计划任务将不再运行。ScheduledThreadExecutor 不仅捕获运行时异常，还允许您在需要时处理它们（通过重写 afterExecute 方法ThreadPoolExecutor）。抛出异常的任务将被取消，但其他任务将继续运行。

####任务调度
Quartz 是一个由 java 编写的任务调度库，由 OpenSymphony 组织开源出来。在实际项目开发中使用 Quartz 的还是居多，比较推荐使用 Quartz。
因为 Quartz 理论上能够同时对上万个任务进行调度，拥有丰富的功能特性，包括任务调度、任务持久化、可集群化、插件等等。

####比较重要的几个类：
    
ExecutorService： 真正的线程池接口。
ScheduledExecutorService： 能和Timer/TimerTask类似，解决那些需要任务重复执行的问题。
ThreadPoolExecutor： ExecutorService的默认实现。
ScheduledThreadPoolExecutor： 继承ThreadPoolExecutor的ScheduledExecutorService接口实现，周期性任务调度的类实现。
要配置一个线程池是比较复杂的，尤其是对于线程池的原理不是很清楚的情况下，
很有可能配置的线程池不是较优的，因此在Executors类里面提供了一些静态工厂，生成一些常用的线程池。

##线程池关闭
Executors作为局部变量时，创建了线程，一定要记得调用executor.shutdown();来关闭线程池，如果不关闭，会有线程泄漏问题。


##线程池大小确定
有一个简单并且适用面比较广的公式：

1. CPU 密集型任务(N+1)： 这种任务消耗的主要是 CPU 资源，可以将线程数设置为 N（CPU 核心数）+1，比 CPU 核心数多出来的一个线程是为了防止线程偶发的缺页中断，或者其它原因导致的任务暂停而带来的影响。一旦任务暂停，CPU 就会处于空闲状态，而在这种情况下多出来的一个线程就可以充分利用 CPU 的空闲时间。
2. I/O 密集型任务(2N)： 这种任务应用起来，系统会用大部分的时间来处理 I/O 交互，而线程在处理 I/O 的时间段内不会占用 CPU 来处理，这时就可以将 CPU 交出给其它线程使用。因此在 I/O 密集型任务的应用中，我们可以多配置一些线程，具体的计算方法是 2N。
虽然线程池大小的设置受到很多因素影响，但是这里给出一个参考公式：
```
最佳线程数目 = （（线程等待时间+线程CPU时间）/线程CPU时间 ）* CPU数目
```
比如平均每个线程CPU运行时间为0.5s，而线程等待时间（非CPU运行时间，比如IO）为1.5s，CPU核心数为8，那么根据上面这个公式估算得到：((0.5+1.5)/0.5)*8=32。这个公式进一步转化为：
```
最佳线程数目 = （线程等待时间与线程CPU时间之比 + 1）* CPU数目
```
线程等待时间所占比例越高，需要越多线程。线程CPU时间所占比例越高，需要越少线程。

[引用](https://github.com/Snailclimb/JavaGuide/blob/master/docs/java/Multithread/java%E7%BA%BF%E7%A8%8B%E6%B1%A0%E5%AD%A6%E4%B9%A0%E6%80%BB%E7%BB%93.md#53-cachedthreadpool-%E8%AF%A6%E8%A7%A3)