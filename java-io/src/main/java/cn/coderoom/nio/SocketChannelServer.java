package cn.coderoom.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class SocketChannelServer {

    public static void main(String[] args) throws Exception
    {
        // 创建选择器
        Selector selector = Selector.open();
        // 打开监听信道
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 与本地端口绑定
        serverSocketChannel.socket().bind(new InetSocketAddress(8080));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 将选择器绑定到监听信道,只有非阻塞信道才可以注册选择器.并在注册过程中指出该信道可以进行Accept操作
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true)
        {
            // 等待某信道就绪
            int selectInt = selector.select();
            if (selectInt == 0)
                continue;
            // 取得迭代器.selectedKeys()中包含了每个准备好某一I/O操作的信道的SelectionKey
            Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
            while (iterator.hasNext())
            {
                SelectionKey selectionKey = iterator.next();
                // 有客户端连接请求时
                if (selectionKey.isAcceptable())
                    handleAccept(selectionKey);
                // 从客户端读取数据
                if (selectionKey.isReadable())
                    handleRead(selectionKey);
                // 向客户端发送数据
                if (selectionKey.isWritable())
                    handleWrite(selectionKey);
                iterator.remove();
            }
        }
    }


    public static void handleAccept(SelectionKey selectionKey) throws Exception
    {
        // 返回创建此键的通道，接受客户端建立连接的请求，并返回 SocketChannel 对象
        ServerSocketChannel ServerSocketChannel = (ServerSocketChannel)selectionKey.channel();
        SocketChannel clientChannel = ServerSocketChannel.accept();
        // 非阻塞式
        clientChannel.configureBlocking(false);
        // 注册到selector
        clientChannel.register(selectionKey.selector(), SelectionKey.OP_READ);

        sendInfo(clientChannel, "连接服务器成功!");
        System.err.println("client IP :" + clientChannel.socket().getRemoteSocketAddress());
    }

    public static void handleRead(SelectionKey key) throws Exception
    {
        SocketChannel channel = (SocketChannel) key.channel();
        String msg = "";
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        while(channel.read(buffer) > 0)
        {
            buffer.flip();
            while(buffer.hasRemaining())
                msg += new String(buffer.get(new byte[buffer.limit()]).array());
            buffer.clear();
        }

        System.err.println("收到客户端消息:"+msg);

        sendInfo(channel, "服务端消息test!");
    }

    public static void handleWrite(SelectionKey key)
    {
        System.out.println("服务端发送信息!");
    }

    public static void sendInfo(SocketChannel clientChannel, String msg) throws Exception
    {
        // 向客户端发送连接成功信息
        clientChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }

}
