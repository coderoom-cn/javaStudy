package cn.coderoom.nio.sockechannel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * https://www.cnblogs.com/coding400/p/9846139.html
 */
public class NioBlockSocketChannelServer {

    public static void main(String[] args) throws IOException {

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        // 监听 8080 端口进来的 TCP 链接
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));

        while (true) {

            // 这里会阻塞，直到有一个请求的连接进来,，我们这里是马上就新建线程来处理这个 SocketChannel 了，
            // 但是，但是这里不代表对方就将数据传输过来了。
            // 所以，SocketChannel#read 方法将阻塞，等待数据，明显这个等待是不值得的。
            // 同理，write 方法也需要等待通道可写才能执行写入操作，这边的阻塞等待也是不值得的。
            SocketChannel socketChannel = serverSocketChannel.accept();

            // 开启一个新的线程来处理这个请求，然后在 while 循环中继续监听 8080 端口
            NioBlockSocketChannelServerHandle handler = new NioBlockSocketChannelServerHandle(socketChannel);
            new Thread(handler).start();
        }
    }

}
