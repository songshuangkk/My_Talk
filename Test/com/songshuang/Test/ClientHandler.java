package com.songshuang.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

import com.songshuang.Test.Method.ChatMethod;
import com.songshuang.Test.Method.ClientChat;
import com.songshuang.Test.Package.TestPackage;

import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ClientHandler extends SimpleChannelInboundHandler<Object>{
	
	private BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
	private String line = null;
	private int login = 0;
	private TestPackage pack = new TestPackage();
	
	 @Override
	 public void messageReceived(ChannelHandlerContext ctx, Object in) throws Exception {
		 String msg = (String)in;
		 msg = pack.Resolve(msg);
		 String head = "";
		 if (msg.equals("请输入用户名:")){
			 System.out.println(msg);
			 head = "USERNAME:";
			 line = read.readLine();
			 line = head + line;
		 } else if (msg.equals("请输入用户密码:")){
			 System.out.println(msg);
			 head = "PASSWORD:";
			 line = read.readLine();
			 line = head + line;
		 } else if (msg.equals("注册新用户:")){
			head = "REGISTER:"; 
			System.out.print("新用户名:");
			line = head + "USERNAME:" + read.readLine();
		 } else if (msg.equals("请输入用户新密码")){
			System.out.print("新用户密码:");
			head = "REGISTER:"; 
			line = "PASSWORD:" + read.readLine();
			line = head + line;
		 } else if (msg.equals("登入成功！")){
			 head = "FUNCTION:";	// 将发送的head进行转换为功能选择标志
			 login = 1;				// 将登入标志设置为1
			 line = ClientChat.getInstance().run();// 进行聊天内容的选择和操作
			 line = head + line;
		 } else {
			 System.out.println(msg);
			 line = read.readLine();
		 }
		 
//		 if (login == 1){
//			 line = head + ChatMethod.getInstance().run();// 进行聊天内容
//		 }

		if (line.equals("byte")) {
			System.out.println("Exit ......");
			ctx.close().sync();
		}
		ctx.writeAndFlush(line + "\r\n");

	 }
	
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		cause.printStackTrace();
		ctx.close();
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx){
		ctx.flush();
	}
}
