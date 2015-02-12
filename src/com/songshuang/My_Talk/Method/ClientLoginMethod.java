package com.songshuang.My_Talk.Method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import io.netty.channel.ChannelHandlerContext;

public class ClientLoginMethod {
	
	ChannelHandlerContext ctx = null;
	String msg;
	
	public ClientLoginMethod(ChannelHandlerContext ctx, String msg){
		System.out.println("1");
		this.ctx = ctx;
		this.msg = msg;
		System.out.println("2");
		this.runLogin();
	}
	
	public void runLogin(){
		String line = null;
		System.out.println("1");
		BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
		try{
			line = read.readLine();
			this.ctx.writeAndFlush(line+"\r\n");
		} catch (IOException e){
			e.printStackTrace();
			this.ctx.close();
		}
	}

}
