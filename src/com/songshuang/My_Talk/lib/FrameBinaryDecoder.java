package com.songshuang.My_Talk.lib;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.songshuang.My_Talk.Configure.Sysconstants;
import com.songshuang.My_Talk.Manager.NetWorkManager;
import com.songshuang.My_Talk.Package.MyTalk_Package;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class FrameBinaryDecoder extends  ByteToMessageDecoder{
	
	private static final Logger logger = LoggerFactory.getLogger(ByteToMessageDecoder.class);
	
	@Override
	protected final void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < Sysconstants.PROTOCOL_HEADER_LENGTH){
			out.add("");
			logger.info("There is no data");
			return;
		}
		
		in.markReaderIndex(); 
		int length = in.readInt();
		char commandId = in.readChar();	// 用于判断是传输文件还是传输聊天消息
		int contentLength = length - Sysconstants.PROTOCOL_HEADER_LENGTH; // 除了包头之外的数据的长度
		
		if (in.readableBytes() < contentLength){
			logger.info("数据长度:" + contentLength);
			in.resetReaderIndex();	// 返回上面标记的位置
			return;
		}
		ByteBuf buffer = Unpooled.buffer(contentLength);
		in.readBytes(buffer, Sysconstants.PROTOCOL_HEADER_LENGTH, contentLength);
		byte[] bytes = new byte[contentLength];
		buffer.readBytes(bytes);
		
		MyTalk_Package pack = new MyTalk_Package();
		// 将获取的信息放入到包中
		pack.setLength(length);
		pack.setCommandId(commandId);
		pack.setContentLength(contentLength);
		pack.setDataBuffer(bytes.toString());
		
		out.add(pack);
	}
}
