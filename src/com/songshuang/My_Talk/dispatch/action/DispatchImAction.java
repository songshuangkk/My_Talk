package com.songshuang.My_Talk.dispatch.action;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.group.ChannelGroup;

import com.songshuang.My_Talk.Dao.UserDao;
import com.songshuang.My_Talk.dispatch.Dispatch;

public class DispatchImAction implements Dispatch{
	
	private ChannelHandlerContext ctx = null;
	private String msg = null;
	
	public DispatchImAction(ChannelHandlerContext ctx, String msg){
		this.ctx = ctx;
		this.msg = msg;
	}

	
	/* *
	 *  
	 *  function : 进行功能的转发
	 * 
	 * */
	@Override
	public void DispatchAction(String msg, ChannelHandlerContext ctx, ChannelGroup group) throws Exception {
		// TODO Auto-generated method stub
		if (msg.equals("ls")){	// 获取当前在线人数
			UserDao user = new UserDao();
			String userList = user.SelectUser();
			ctx.writeAndFlush(userList+"\r\n");
		}
	}

}
