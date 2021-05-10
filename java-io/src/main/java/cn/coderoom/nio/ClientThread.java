package cn.coderoom.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class ClientThread implements Runnable{

    private Selector selector;
    /**
     * 客户端写进程
     * @param selector
     */
    public ClientThread(Selector selector){
        this.selector = selector;
        new Thread(this).start();
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try{
            // selector.select() 未阻塞方法。
            while(selector.select()>0){
                for(SelectionKey key:selector.selectedKeys()){
                    if(key.isReadable()){
                        //使用NIO嘟嘟channel中的数据
                        SocketChannel clientchannel = (SocketChannel)key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        clientchannel.read(buffer);
                        buffer.flip();
                        //将字节转化为UTF-16的字符串
                        String receivedString = Charset.forName("UTF-8").newDecoder().decode(buffer).toString();
                        System.out.println("服务器"+clientchannel.socket().getRemoteSocketAddress()+":"+receivedString);
                        //为下次读取准备
                        key.interestOps(SelectionKey.OP_READ);
                    }
                    //删除正在处理的selectionkey
                    selector.selectedKeys().remove(key);
                }

            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


}

