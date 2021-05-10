package cn.coderoom.nio.sockechannel;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * 来一个新的连接，我们就新开一个线程来处理这个连接，之后的操作全部由那个线程来完成。
 */
public class NioBlockSocketChannelServerHandle implements Runnable {

    private SocketChannel socketChannel;

    public NioBlockSocketChannelServerHandle(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    @Override
    public void run() {

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        try {
            // 将请求数据读入 Buffer 中
            int num;
            while ((num = socketChannel.read(buffer)) > 0) {
                // 读取 Buffer 内容之前先 flip 一下
                buffer.flip();

                // 提取 Buffer 中的数据
                byte[] bytes = new byte[num];
                buffer.get(bytes);

                String re = new String(bytes, "UTF-8");
                System.out.println("收到请求：" + re);

                // 回应客户端
                ByteBuffer writeBuffer = ByteBuffer.wrap(("我已经收到你的请求，你的请求内容是：" + re).getBytes());
                socketChannel.write(writeBuffer);

                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
