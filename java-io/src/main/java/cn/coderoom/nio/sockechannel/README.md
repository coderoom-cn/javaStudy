
#### 阻塞式读
```java
SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
ByteBuffer byteBuffer = ByteBuffer.allocate(16);
socketChannel.read(byteBuffer);
socketChannel.close();
System.out.println("test end!");
```

#### 非阻塞读
```java
SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("www.baidu.com", 80));
socketChannel.configureBlocking(false);
ByteBuffer byteBuffer = ByteBuffer.allocate(16);
socketChannel.read(byteBuffer);
socketChannel.close();
System.out.println("test end!");
```