package com.songshuang.My_Talk.Manager;

import com.songshuang.My_Talk.Dao.LoginDao;

import io.netty.channel.ChannelHandlerContext;

public class ServerLogin {
	ChannelHandlerContext ctx = null;
	Object in = null;
	LoginDao login = null;
	
	public ServerLogin(){
		
	}
	
	public ServerLogin(ChannelHandlerContext ctx, Object obj){
		this.ctx = ctx;
		this.in  = obj;
		this.login = new LoginDao();
	}
	
	public void run(String name, String password) throws Exception{
		login.checkLogin(name, password);
	}
	
}
