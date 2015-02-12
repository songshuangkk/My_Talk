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
	
	private int loginFlag = 0;	// ���û��ɹ����븳ֵΪ1
	
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
		/*��״̬�ǵ���������ʱ�򣬽��к˶��û���Ϣ*/
		if (msg.equals("login") && loginFlag == 0){
			/*���õ���ģ�������*/
			ctx.writeAndFlush("�������û���:\r\n");
		} else if (msg.length() > 8 && msg.substring(0, 9).equals("USERNAME:")&& loginFlag == 0){
			ctx.writeAndFlush("�������û�����:\n");
			userName = msg.substring(9);
		} else if (msg.length() > 8 &&msg.substring(0, 9).equals("PASSWORD:")&& loginFlag == 0){
			passWord = msg.substring(9);
			/*�����û����������У��*/
			boolean check = CheckPersonal.getInstance().check(userName, passWord);
			if (check){
				ctx.writeAndFlush("����ɹ���\n");
				loginFlag = 1;
			} else {
				ctx.writeAndFlush("�û�������������\n");
			}
			
		} else if (loginFlag == 0 && msg.equals("login") && msg.equals("USERNAME:") && msg.equals("PASSWORD:")){ 
			ctx.writeAndFlush("�û���������\n");
		} else  if (loginFlag == 1){
			/*���õ���ģ����������Ӧ�Ĳ���*/
//			new ServerLogin(ctx, in).run(userName, passWord);
		} else {
			ctx.writeAndFlush("�û���������\n");
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
