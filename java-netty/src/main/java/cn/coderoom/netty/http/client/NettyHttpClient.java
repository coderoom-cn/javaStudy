package cn.coderoom.netty.http.client;

import cn.coderoom.netty.http.client.handler.NettyHttpClientHandler;
import cn.coderoom.netty.http.client.handler.NettyHttpHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NettyHttpClient {

    public void connect(String host, int port) throws Exception {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.SO_KEEPALIVE, true);
            b.handler(new ChannelInitializer<SocketChannel>() {
                @Override
                public void initChannel(SocketChannel ch) throws Exception {
                    // 客户端接收到的是httpResponse响应，所以要使用HttpResponseDecoder进行解码
                    ch.pipeline().addLast(new HttpResponseDecoder());
                    // 客户端发送的是httprequest，所以要使用HttpRequestEncoder进行编码
                    ch.pipeline().addLast(new HttpRequestEncoder());
                    ch.pipeline().addLast(new NettyHttpClientHandler());
                }
            });
            ChannelFuture f = b.connect(host, port).sync();
            URI uri = new URI("http://112.124.52.157:8091");
            String msg = "Are you ok?";
            DefaultFullHttpRequest request = new DefaultFullHttpRequest(
                    HttpVersion.HTTP_1_1, HttpMethod.POST, uri.toASCIIString(),
                    Unpooled.wrappedBuffer(msg.getBytes()));
            // 构建http请求
            request.headers().set(HttpHeaderNames.HOST, host);
            request.headers().set(HttpHeaderNames.CONNECTION,
                    HttpHeaderNames.CONNECTION);
            request.headers().set(HttpHeaderNames.CONTENT_LENGTH,
                    request.content().readableBytes());
            request.headers().set("messageType", "normal");
            request.headers().set("businessType", "testServerState");
            // 发送http请求
            f.channel().write(request);
            f.channel().flush();
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
    /*public static void main(String[] args) throws Exception {
        NettyHttpClient client = new NettyHttpClient();
//        client.connect("127.0.0.1", 8000);
        client.connect("112.124.52.157", 8091);
    }*/

    public static void main(String [] f) {
        System.out.println(new NettyHttpClient().send("GET"));
//        System.out.println(new NettyHttpClient().send("POST"));
    }

    public String send(String msg) {
        try {
            Map<String, Object> map = new ConcurrentHashMap<>();
            map.put("server","112.124.52.157:8091");
            map.put("uri","/dy/douyindeviceinfo/getGoodsInfos");
            send(msg, map);
            return (String)map.get("res");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void send(String msg, Map<String, Object> map) {
        try {

            final Bootstrap b = BootStrapManager.newBootStrap();
            b.handler(new ClientInit(new NettyHttpHandler(map, msg), SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE).build()));
            ChannelFuture f = b.connect("112.124.52.157", 8091);
            f.channel().closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

        }
    }

    private static class ClientInit extends ChannelInitializer<SocketChannel> {

        private ChannelInboundHandler handler;
        private SslContext context;

        public ClientInit(ChannelInboundHandler handler, SslContext context) {
            this.handler = handler;
            this.context = context;
        }

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
            /*if (true) {
                ch.pipeline().addLast(context.newHandler(ch.alloc()));
            }*/
            ch.pipeline().addLast(new HttpResponseDecoder());
            ch.pipeline().addLast(new HttpRequestEncoder());
            ch.pipeline().addLast(new HttpObjectAggregator(65535));
            ch.pipeline().addLast(handler);
        }
    }

    private static class BootStrapManager {

        private static final EventLoopGroup WORKER_GROUP = new NioEventLoopGroup();
        private static Bootstrap CLIENT_BOOTSTRAP ;

        public static Bootstrap getBootStrap() {
            if(CLIENT_BOOTSTRAP == null) {
                synchronized (BootStrapManager.class) {
                    if(CLIENT_BOOTSTRAP == null) {
                        CLIENT_BOOTSTRAP = newBootStrap();
                    }
                }
            }
            return CLIENT_BOOTSTRAP;
        }

        public static Bootstrap newBootStrap() {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(WORKER_GROUP);
            bootstrap.channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.SO_KEEPALIVE, false);
            bootstrap.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000);

            return bootstrap;
        }
    }

}
