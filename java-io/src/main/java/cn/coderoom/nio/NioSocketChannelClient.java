package cn.coderoom.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 代码执行顺序: 静态代码块——》main 方法——》普通代码块——》构造方法——》普通方法
 */
public class NioSocketChannelClient {

    SocketChannel socketChannel;

    private String hostIp;
    private int port;

    //固定大小的线程池，跟回收型线程池类似，只是可以限制同时运行的线程数量
    private final ExecutorService workerExecutor = Executors.newFixedThreadPool(1, r -> new Thread(r,"worker-"));
    private final ExecutorService bossExecutor = Executors.newFixedThreadPool(1, r -> new Thread(r,"boss-"));

    private final ByteBuffer headerBuffer = ByteBuffer.allocate(3);

    /**
     * 构造函数
     * @param hostIp
     * @param port
     * @throws IOException
     */
    public NioSocketChannelClient(String hostIp,int port) {
        this.hostIp = hostIp;
        this.port = port;

        initialize();
    }

    /**
     * 初始化
     * @throws IOException
     */
    public void initialize() {

        bossExecutor.execute(() -> {

            if (isConnected()) {
                return;
            }

            try {

                //打开监听信道并设置为非阻塞模式
                socketChannel = SocketChannel.open();
                socketChannel.configureBlocking(true);
                socketChannel.socket().setTcpNoDelay(true);
                socketChannel.socket().setKeepAlive(true);
                socketChannel.socket().setReceiveBufferSize(2048);
                socketChannel.socket().setSendBufferSize(1024);
                socketChannel.socket().connect(new InetSocketAddress(hostIp, port),10 * 1000);

                this.receiver();

            } catch (IOException e) {
                e.printStackTrace();
            }

        });


    }

    public void sendMsg(String message) {

        if(!isConnected()) {
            return;
        }

        workerExecutor.execute(() -> {
            int result = 0;
            try {

                ByteBuffer buffer = ByteBuffer.wrap(message.getBytes("UTF-8"));
                socketChannel.write(buffer);

            } catch (Exception e) {
                result = -1;
            }finally {
                System.out.println("=================发送完成");
            }
        });
    }

    public boolean isConnected() {
        return socketChannel != null && socketChannel.isConnected();
    }

    /**
     * 接受server发送数据
     * @throws IOException
     */
    public void receiver() throws IOException {

        /**
         * 在主线程中使用while会阻塞线程，需要放在子线程中。
         */
        while (true) {

            ByteBuffer buffer = ByteBuffer.allocate(1024);
            socketChannel.read(buffer);// 阻塞方法，线程停止运行
            buffer.flip();
            //将字节转化为UTF-16的字符串
            String receivedString = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
            System.out.println("服务器"+socketChannel.socket().getRemoteSocketAddress()+":"+receivedString);

            System.out.println("服务器receivedString==="+receivedString);

        }

    }
    public static void main(String[] args) {

        NioSocketChannelClient client = new NioSocketChannelClient("127.0.0.1",8080);
        Scanner read = new Scanner(System.in);
        //client.receiver();
        while(true) {
            System.out.print("客户端:");
            String msg = read.next();
            client.sendMsg(msg);
        }
    }

}
