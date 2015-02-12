package com.songshuang.My_Talk.Method;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;

public class ServerLoginMethod {
	ChannelHandlerContext ctx = null;
	//ByteBuf in = null;
	Object in = null;
	
	public ServerLoginMethod(ChannelHandlerContext ctx, Object in){
		this.ctx = ctx;
		this.in =  in;
	}
	
	/* *
	 * 	
	 *  function : 用户登入成功进行一个聊天操作
	 *  return 	 : null
	 * 
	 * */
	public void run(){
		this.ctx.writeAndFlush("hahahhhah\r\n");
	}
}
