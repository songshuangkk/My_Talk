package com.songshuang.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.songshuang.My_Talk.TalkMainServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.concurrent.GenericFutureListener;

public class Client {
	
	private static final Logger logger = LoggerFactory.getLogger(Client.class);
	
	public void run(int port , String host) throws Exception{
		this.connect(port, host);
	}
	
	public void connect(int port, String host){
		EventLoopGroup group = new NioEventLoopGroup();
		try{
			Bootstrap bootstrap = new Bootstrap();
			bootstrap.group(group)
			.channel(NioSocketChannel.class)
			.option(ChannelOption.TCP_NODELAY, true)
			.option(ChannelOption.SO_KEEPALIVE, true)
			.handler(new ClientInitializer());
			ChannelFuture future = bootstrap.connect(host, port).sync();
			future.channel().closeFuture().sync();
		} catch (InterruptedException e){
			e.printStackTrace();
		} finally {
			group.shutdownGracefully();
		}
	}
	
	public static void main(String[] args){
		try{
			new Client().run(9982, "localhost");
		} catch (Exception e){
			logger.info(""+ e);
		}
	}

}
