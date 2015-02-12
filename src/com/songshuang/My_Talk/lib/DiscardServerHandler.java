package com.songshuang.My_Talk.lib;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

public class DiscardServerHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg){
		System.out.println(msg);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}

}
