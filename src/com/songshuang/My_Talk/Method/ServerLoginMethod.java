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
	 *  function : �û�����ɹ�����һ���������
	 *  return 	 : null
	 * 
	 * */
	public void run(){
		this.ctx.writeAndFlush("hahahhhah\r\n");
	}
}
