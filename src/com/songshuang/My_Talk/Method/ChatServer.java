package com.songshuang.My_Talk.Method;

import com.songshuang.My_Talk.dispatch.Dispatch;
import com.songshuang.My_Talk.dispatch.action.DispatchImAction;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

public class ChatServer {
	
	private ChannelHandlerContext ctx;
	private ChannelGroup group = null;
	
	private static ChatServer chatServer = null;
	
	public ChatServer(ChannelHandlerContext ctx, ChannelGroup group){
		this.ctx = ctx;
		this.group = group;
	}
	
	public static ChatServer getInstance(ChannelHandlerContext ctx, ChannelGroup group){
		if (chatServer == null){
			chatServer = new ChatServer(ctx, group);
		}
		return chatServer;
	}
	
	/* *
	 * 
	 * �����ж��û�����ش������ô���ַ���
	 * 
	 * */
	public void run(String msg) throws Exception{
		chatServer.dispatch(msg);
	}

	private void dispatch(String msg) throws Exception {
		// TODO Auto-generated method stub
		Dispatch dispatch = new DispatchImAction(ctx, msg);
		dispatch.DispatchAction(msg, ctx, group);
	}
}
