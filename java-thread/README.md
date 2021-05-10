线程池主要有四个：

newSingleThreadExecutor：单线程池，同时只有一个线程在跑(优点: 串行执行所有任务)。分析源码可以看出，它有些类似于 newFixedThreadPool，可以把它当作特殊的 newFixedThreadPool。
newCachedThreadPool() ：回收型线程池，可以重复利用之前创建过的线程，运行线程最大数是Integer.MAX_VALUE。
newFixedThreadPool() ：固定大小的线程池，跟回收型线程池类似，只是可以限制同时运行的线程数量。
ScheduledExecutorService,是基于线程池设计的定时任务类,每个调度任务都会分配到线程池中的一个线程去执行,也就是说,任务是并发执行,互不影响。