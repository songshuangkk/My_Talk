package com.songshuang.My_Talk.Manager;

import java.net.InetAddress;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ChannelFactory;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpClientCodec;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.CharsetUtil;

import com.songshuang.My_Talk.Configure.Configure;
import com.songshuang.My_Talk.Configure.Sysconstants;
import com.songshuang.My_Talk.Init.ServerChannelInit;
import com.songshuang.My_Talk.lib.DiscardServerHandler;
import com.songshuang.My_Talk.lib.FrameBinaryDecoder;

public class NetWorkManager {
	
	private static final Logger logger = LoggerFactory.getLogger(NetWorkManager.class);
	
	private static NetWorkManager netWorkManager;
	
	private int port = Sysconstants.SERVER_DEFAULT_PORT;
	
	private EventLoopGroup bossGroup;
	private EventLoopGroup workerGroup;
	private ServerBootstrap bootstrap;	// ServerÒýµ¼Àà
	
	public NetWorkManager(){
		
	}
	
	public void init(int port){
		bossGroup 	 = new NioEventLoopGroup();
		workerGroup  = new NioEventLoopGroup();
		
		try{
			bootstrap 	 = new ServerBootstrap();
			bootstrap.group(bossGroup, workerGroup)
			.channel(NioServerSocketChannel.class)
			.childHandler(new ServerChannelInit())
			.option(ChannelOption.SO_BACKLOG, 128)
			.childOption(ChannelOption.SO_KEEPALIVE, true);
			ChannelFuture f = bootstrap.bind(port);
			f.channel().closeFuture().sync();	
		} catch(Exception e){
			logger.info("", e);
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public static NetWorkManager getInstance(){
		if (netWorkManager == null){
			netWorkManager = new NetWorkManager();
		}
		return netWorkManager;
	}
}
