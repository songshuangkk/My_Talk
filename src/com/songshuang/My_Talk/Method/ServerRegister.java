package com.songshuang.My_Talk.Method;

import io.netty.channel.ChannelHandlerContext;

import com.songshuang.My_Talk.Dao.RegisterDao;
import com.songshuang.My_Talk.Manager.DataSoucrceManager;

public class ServerRegister {
	
	RegisterDao register = null;
	ChannelHandlerContext ctx = null;
	Object in = null;
	
	public ServerRegister(ChannelHandlerContext ctx, Object in){
		this.ctx = ctx;
		this.in  = in;
		this.register = new RegisterDao();
	}
	
	public void run(String name, String password) throws Exception{
		if (this.register.registerUser(name, password))
		{
			this.ctx.writeAndFlush("×¢²á³É¹¦ !\r\n");
		} else{
			this.ctx.writeAndFlush("×¢²áÊ§°Ü!\r\n");
		}
	}

}
