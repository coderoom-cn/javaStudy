package cn.coderoom.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * socketChannel.isOpen();      // 测试SocketChannel是否为open状态
 * socketChannel.isConnected();    //测试SocketChannel是否已经被连接
 * socketChannel.isConnectionPending();    //测试SocketChannel是否正在进行连接
 * socketChannel.finishConnect();    //校验正在进行套接字连接的SocketChannel是否已经完成连接
 */
public class SocketChannelClient {

    private Selector selector;
    SocketChannel socketchannel;

    private String hostIp;
    private int port;
    /**
     * 构造函数
     * @param hostIp
     * @param port
     * @throws IOException
     */
    public SocketChannelClient(String hostIp,int port)throws IOException {
        this.hostIp = hostIp;
        this.port = port;

        initialize();
    }

    /**
     * 初始化
     * @throws IOException
     */
    public void initialize()throws IOException{
        //打开监听信道并设置为非阻塞模式
        socketchannel = SocketChannel.open(new InetSocketAddress(hostIp,port));
        socketchannel.configureBlocking(false);
        //打开并注册到信道
        selector = Selector.open();
        socketchannel.register(selector, SelectionKey.OP_READ);

        //启动读线程
        new ClientThread(selector);
    }

    public static void main(String[] args) throws Exception {

        SocketChannelClient client = new SocketChannelClient("127.0.0.1",8080);
        Scanner read = new Scanner(System.in);
        int i =0;
        while(true) {
            System.out.println("本地:");
            String msg = read.next();
            client.sendMsg(msg);
            System.out.println("i===================="+i);
            i++;
        }
    }


    public void sendMsg(String message) throws IOException {
        ByteBuffer writeBuffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
        socketchannel.write(writeBuffer);
    }

    public static void handleConnect(SelectionKey key) throws Exception {
        SocketChannel channel = (SocketChannel) key.channel();
        if (channel.isConnectionPending())
            channel.finishConnect();
        channel.configureBlocking(false);
        channel.register(key.selector(), SelectionKey.OP_READ);
        System.out.println("==========客户端写数据!");
        sendInfo(channel, "客户端测试!");
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

        System.err.println("收到服务端消息:"+msg);
    }

    public static void handleWrite(SelectionKey key) throws Exception
    {
        System.err.println("客户端写数据!");
    }

    public static void sendInfo(SocketChannel clientChannel, String msg) throws Exception
    {
        // 向客户端发送连接成功信息
        clientChannel.write(ByteBuffer.wrap(msg.getBytes()));
    }


}
