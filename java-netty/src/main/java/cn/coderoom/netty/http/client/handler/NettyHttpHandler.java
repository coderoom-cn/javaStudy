package cn.coderoom.netty.http.client.handler;

import cn.coderoom.netty.http.client.utils.HttpRequestCreateor;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpRequest;
import org.apache.commons.io.Charsets;

import java.net.URI;
import java.util.Map;

public class NettyHttpHandler extends SimpleChannelInboundHandler<FullHttpResponse> {

    private Map<String, Object> map;
    private String msg;

    public NettyHttpHandler(Map _map, String msg) {
        this.map = _map;
        this.msg = msg;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        System.out.println("active");
        HttpRequest request = null;
        if (msg.equals("GET")){
            String server = map.get("server").toString();
            String uri = map.get("uri").toString();
            request = HttpRequestCreateor.createReqGet(server, new URI(uri));
        }


        if (msg.equals("POST"))
            request = HttpRequestCreateor.createReqPost(
                    "{\"pa\":\"BeJson\",\"pb\":\"sss\"}".getBytes(), "localhost", new URI("/json/testhttpsPost"));


        // 发送
        ctx.channel().writeAndFlush(request).addListener(new ChannelFutureListener() {

            @Override
            public void operationComplete(ChannelFuture future) throws Exception {

            }
        });

    }

    /**
     * http reponse 相应处理
     * @param ctx
     * @param msg
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpResponse msg) {

        if (msg instanceof HttpContent) {
            HttpContent httpContent = (HttpContent) msg;
            // 字符数组
            ByteBuf buf = httpContent.content();
            // 返回
            String response = buf.toString(Charsets.UTF_8);
            map.put("res", response);
            ctx.close();
        }
    }
}