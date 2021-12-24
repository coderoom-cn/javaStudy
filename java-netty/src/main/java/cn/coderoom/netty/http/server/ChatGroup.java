package cn.coderoom.netty.http.server;

import io.netty.channel.group.ChannelGroup;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class ChatGroup {

    /**
     * 已连接客户端集合
     */
    public  static ConcurrentMap<String, ChannelGroup> chatGroupMap=new ConcurrentHashMap<>();
}