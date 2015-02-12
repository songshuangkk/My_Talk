package com.songshuang.My_Talk.dispatch;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

public interface Dispatch {
	void DispatchAction(String msg, ChannelHandlerContext ctx, ChannelGroup group) throws Exception;
}
