package cn.coderoom.netty.http.client.utils;

import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.*;
import org.apache.commons.io.Charsets;

import java.net.URI;

public class HttpRequestCreateor {

    /**
     * 构造HTTP请求
     * @param server ip、域名
     * @param uri 除域名外的地址
     * @return
     * @throws Exception
     */
    public static HttpRequest createReqGet(String server, URI uri) {
        String req = "";
        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET,
                uri.toASCIIString(), Unpooled.wrappedBuffer(req.getBytes(Charsets.UTF_8)));
        // 构建HTTP请求
        request.headers().set(HttpHeaders.Names.HOST, server);
        request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        request.headers().set("accept-type", Charsets.UTF_8);
        request.headers().set(HttpHeaders.Names.CONTENT_TYPE, "application/json; charset=UTF-8");
        //    request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
        // 返回
        return request;
    }

    public static HttpRequest createReqPost(byte [] body, String server, URI uri) throws Exception{

        DefaultFullHttpRequest request = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.POST,
                uri.toASCIIString(), Unpooled.wrappedBuffer(body));
        // 构建HTTP请求
        request.headers().set(HttpHeaders.Names.HOST, server);
        request.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE);
        request.headers().set("accept-type", Charsets.UTF_8);
        request.headers().set(HttpHeaders.Names.CONTENT_TYPE, "application/json; charset=UTF-8");
        request.headers().set(HttpHeaders.Names.CONTENT_LENGTH, request.content().readableBytes());
        // 返回
        return request;
    }

}
