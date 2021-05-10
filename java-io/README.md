
## NIO tutorial
https://www.cnblogs.com/coding400/p/9846139.html
http://tutorials.jenkov.com/java-nio/index.html

### TCP

TCP长/短连接操作过程
3.1 短连接的操作步骤是：
建立连接——数据传输——关闭连接...建立连接——数据传输——关闭连接
3.2 长连接的操作步骤是：
建立连接——数据传输...（保持连接）...数据传输——关闭连接

#### 长连接
模拟一种长连接的情况:
client 向 server 发起连接
server 接到请求，双方建立连接
client 向 server 发送消息
server 回应 client
一次读写完成，连接不关闭
后续读写操作...
长时间操作之后client发起关闭请求

##### 应用场景
长连接多用于操作频繁，点对点的通讯，而且连接数不能太多情况。
每个TCP连接都需要三次握手，这需要时间，如果每个操作都是先连接，
再操作的话那么处理速度会降低很多，所以每个操作完后都不断开，
再次处理时直接发送数据包就OK了，不用建立TCP连接。
例如：数据库的连接用长连接，如果用短连接频繁的通信会造成socket错误，
而且频繁的socket 创建也是对资源的浪费。
#### 短链接
模拟一种TCP短连接的情况:
client 向 server 发起连接请求
server 接到请求，双方建立连接
client 向 server 发送消息
server 回应 client
一次读写完成，此时双方任何一个都可以发起 close 操作
在步骤5中，一般都是 client 先发起 close 操作。当然也不排除有特殊的情况。
从上面的描述看，短连接一般只会在 client/server 间传递一次读写操作！

##### 应用场景
像WEB网站的http服务一般都用短链接，因为长连接对于服务端来说会耗费一定的资源，
而像WEB网站这么频繁的成千上万甚至上亿客户端的连接用短连接会更省一些资源，
如果用长连接，而且同时有成千上万的用户，如果每个用户都占用一个连接的话，
那可想而知吧。所以并发量大，但每个用户无需频繁操作情况下需用短连好。

##### Socket TCP Server一个端口可以有多少个长连接？
网上答案很多，不知道那个才是正确的 
理论上是无限的，而客户端有65535个。

16、Linux中，一个端口能够接受tcp链接数量的理论上限是?
A.1024
B.65535
C.65535 * 65535
D.无上限
参考答案：D

重点：但是会受到打开的最大文件数量和内存决定的
Linux上连接数，理论上可以达到没有上限，但实际上由于Linux中一切都是文件，Linux允许打开的文件的句柄数的上限决定...
Linux允许打开的文件的句柄数的上限为65535？.
没有修改配置情况下，查看 ulimit -n 是1024， 但是可以修改的 具体是根据内存决定的 ，不知道网上所说的上限为65535是为什么？
因为ulimit -n 是限制用户的打开文件的最大上限，可以修改，决定于limits.conf的值
ulimit -n 和limits.conf里最大文件数设定不能超过/proc/sys/fs/file-max的值，这也是搞笑了，
/proc/sys/fs/file-max是系统给出的建议值，系统会计算资源给出一个和合理值，一般跟内存有关系，内存越大，改值越大，但是仅仅是一个建议值，limits.conf的设定完全可以超过/proc/sys/fs/file-max

那么可以 总结出 一个端口可以有多少个长连接 是无限个的，ulimit -n 的上限也不是 65535，文件数上限也是归根到底根据内存的大小决定的


### NIO
Socket属于IO的一种，nio提供了ServerSocketChannel和SocketChannel。
普通Socket和NioSocket的区别：
    普通Socket是客户端发出一次请求、服务端接收到后响应、客户端接收到服务端的响应才能再次请求。
    NioSocket是引入了三个概念：Channel、Selector、Buffer。Buffer是将很多请求打包，一次性发出去，有Selector扮演选择器的角色，将请求分转给对应的通道（Channel）进行处理响应。

NioSocket中服务端的处理过程分为5步：
    1、创建ServerScoketChannel对象并设置相关参数（绑定监听端口号，是否使用阻塞模式）
    2、创建Selector并注册到服务端套接字信道（ServerScoketChannel）上
    3、使用Selector的select方法等待请求
    4、接收到请求后使用selectedKeys方法获得selectionKey集合
    5、根据选择键获得Channel、Selector和操作类型进行具体处理。
