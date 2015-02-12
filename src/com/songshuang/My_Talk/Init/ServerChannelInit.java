package com.songshuang.My_Talk.Init;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;
import java.util.Date;

import com.songshuang.My_Talk.Handler.ServerChannelHandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class ServerChannelInit extends ChannelInitializer<SocketChannel> {

	@Override
	protected void initChannel(SocketChannel ssc) throws Exception {
		// TODO Auto-generated method stub
		ChannelPipeline pipeline = ssc.pipeline();
		pipeline.addLast("framer", new DelimiterBasedFrameDecoder(8192,
				Delimiters.lineDelimiter()));
		pipeline.addLast("decoder", new StringDecoder());
		pipeline.addLast("encoder", new StringEncoder());
		pipeline.addLast(new ServerChannelHandler());
	}
}
