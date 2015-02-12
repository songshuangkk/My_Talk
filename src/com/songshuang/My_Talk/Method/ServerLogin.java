package com.songshuang.My_Talk.Method;

import com.songshuang.My_Talk.Dao.LoginDao;

import io.netty.channel.ChannelHandlerContext;

public class ServerLogin {
	ChannelHandlerContext ctx = null;
	Object in = null;
	LoginDao  login = null;
	
	public ServerLogin(){
		this.login = new LoginDao();
	}
	
	public ServerLogin(ChannelHandlerContext ctx, Object obj){
		this.ctx = ctx;
		this.in  = obj;
		this.login = new LoginDao();
	}
	
	public boolean run(String name, String password, String channelId) throws Exception{
		return login.checkLogin(name, password, channelId);
	}
	
	public void signOut() throws Exception{
		login.SignOut();
	}
	
}
