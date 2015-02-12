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
		 if (msg.equals("�������û���:")){
			 System.out.println(msg);
			 head = "USERNAME:";
			 line = read.readLine();
			 line = head + line;
		 } else if (msg.equals("�������û�����:")){
			 System.out.println(msg);
			 head = "PASSWORD:";
			 line = read.readLine();
			 line = head + line;
		 } else if (msg.equals("ע�����û�:")){
			head = "REGISTER:"; 
			System.out.print("���û���:");
			line = head + "USERNAME:" + read.readLine();
		 } else if (msg.equals("�������û�������")){
			System.out.print("���û�����:");
			head = "REGISTER:"; 
			line = "PASSWORD:" + read.readLine();
			line = head + line;
		 } else if (msg.equals("����ɹ���")){
			 head = "FUNCTION:";	// �����͵�head����ת��Ϊ����ѡ���־
			 login = 1;				// �������־����Ϊ1
			 line = ClientChat.getInstance().run();// �����������ݵ�ѡ��Ͳ���
			 line = head + line;
		 } else {
			 System.out.println(msg);
			 line = read.readLine();
		 }
		 
//		 if (login == 1){
//			 line = head + ChatMethod.getInstance().run();// ������������
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
