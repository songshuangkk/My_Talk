package com.songshuang.My_Talk.Manager;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Iterator;

import com.songshuang.My_Talk.Method.CheckPersonal;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.ChannelGroupFuture;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.ssl.SslHandler;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;

public class ServerChannelHandler extends SimpleChannelInboundHandler<Object>{
	
	private static ChannelGroup group = new  DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	private static int i;
	private String userName = null;
	private String passWord = null;
	
	private int loginFlag = 0;	// 当用户成功登入赋值为1
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception{
		ctx.writeAndFlush("Welcome to connected\r\n");
		Channel channel = ctx.channel();
		Iterator<Channel> iterator = group.iterator();
		while(iterator.hasNext()){
			Channel tempChannel = iterator.next();
			tempChannel.writeAndFlush("[Client " + i + "]"+" has joinded\r\n");
		}
		group.add(channel);
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception{
		Channel channel = ctx.channel();
		String  disChannelName = channel.getClass().getName();
		group.remove(channel);
		
		Iterator<Channel> iterator = group.iterator();
		while(iterator.hasNext()){
			Channel tempChannel = iterator.next();
			tempChannel.writeAndFlush(disChannelName+" has disconnected\n");
		}
	}
	
	 @Override
	 public void messageReceived(ChannelHandlerContext ctx, Object in) throws Exception {
		String msg = (String)in;
		/*当状态是登入的命令的时候，进行核对用户信息*/
		if (msg.equals("login") && loginFlag == 0){
			/*调用登入模块的命令*/
			ctx.writeAndFlush("请输入用户名:\r\n");
		} else if (msg.length() > 8 && msg.substring(0, 9).equals("USERNAME:")&& loginFlag == 0){
			ctx.writeAndFlush("请输入用户密码:\n");
			userName = msg.substring(9);
		} else if (msg.length() > 8 &&msg.substring(0, 9).equals("PASSWORD:")&& loginFlag == 0){
			passWord = msg.substring(9);
			/*进行用户名和密码的校验*/
			boolean check = CheckPersonal.getInstance().check(userName, passWord);
			if (check){
				ctx.writeAndFlush("登入成功！\n");
				loginFlag = 1;
			} else {
				ctx.writeAndFlush("用户名或密码有误！\n");
			}
			
		} else if (loginFlag == 0 && msg.equals("login") && msg.equals("USERNAME:") && msg.equals("PASSWORD:")){ 
			ctx.writeAndFlush("用户输入有误！\n");
		} else  if (loginFlag == 1){
			/*调用登入模块代码进行相应的操作*/
//			new ServerLogin(ctx, in).run(userName, passWord);
		} else {
			ctx.writeAndFlush("用户输入有误！\n");
		}
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx){
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		ctx.channel().closeFuture().addListener(ChannelFutureListener.CLOSE);
		cause.printStackTrace();
		ctx.close();
	}
}
