package com.songshuang.My_Talk.Handler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import com.songshuang.My_Talk.Method.ChatServer;
import com.songshuang.My_Talk.Method.CheckPersonal;
import com.songshuang.My_Talk.Method.ServerLogin;
import com.songshuang.My_Talk.Method.ServerRegister;

import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
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
	
	private String registerName = null;
	private String registerPassword = null;
	
	private int loginFlag = 0;	// ���û��ɹ����븳ֵΪ1
	private int ChatFlag  = 0;	// ���û�������Ų����ɹ�֮��ֵΪ1
	private String channelId = "";
	private HashMap<String, Channel> channelMap = new HashMap();
	
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
		
		channelId = channel.id().asLongText();	// ȡ��ChannelID
	}
	
	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception{
		Channel channel = ctx.channel();
		String  disChannelName = channel.getClass().getName();
		new ServerLogin().signOut(); // ���û��ĵ�����Ϣ���
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
			boolean check = new ServerLogin(ctx, in).run(userName, passWord, channelId);
			if (check){
				ctx.writeAndFlush("����ɹ���\n");
				loginFlag = 1;
				ChatFlag = 1;
			} else {
				if (loginFlag != 1){
					ctx.writeAndFlush("�û�������������\n");
				}
			}
			
		} else if (msg.equals("register") || msg.substring(0, 9).equals("REGISTER:")){
			if (msg.length() == 8) {
				ctx.writeAndFlush("ע�����û�:\r\n");
			} else {
				msg = msg.substring(9);

				if (msg.length() > 9) {
					String nameTemp = msg.substring(0, 9);
					if (nameTemp.equals("USERNAME:") && msg.length() > 10) {
						registerName = msg.substring(9);
						ctx.writeAndFlush("�������û�������\r\n");
					}
					if (nameTemp.equals("PASSWORD:") && msg.length() > 10) {
						registerPassword = msg.substring(9);
					}
				}  else {
					ctx.writeAndFlush("ע�����û����û��������벻��ȷ");
				}

				if (registerName != null && registerPassword != null) {
					new ServerRegister(ctx, in).run(registerName,
							registerPassword); // �����û���ע��
					registerName = null;
					registerPassword = null;
				}
			}
		}else if (loginFlag == 0 && msg.equals("login") && msg.equals("USERNAME:") && msg.equals("PASSWORD:")){ 
			ctx.writeAndFlush("�û���������\n");
		}  else if (loginFlag == 1 && ChatFlag == 1){
			/* ��������ģ������� */
			msg = msg.substring(9);
			ChatServer.getInstance(ctx, group).run(msg);
		}else {
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
