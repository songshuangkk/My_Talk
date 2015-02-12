package com.songshuang.My_Talk.Package;

import java.util.List;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/* *
 * 
 * 定义传输的数据包的格式
 * 
 * 
 * */
public class MyTalk_Package {
	
	private int 	Length;
	private char 	CommandId;
	private int 	contentLength;
	private String 	dataBuffer;
	
	public String getDataBuffer() {
		return dataBuffer;
	}
	public void setDataBuffer(String dataBuffer) {
		this.dataBuffer = dataBuffer;
	}
	public int getLength() {
		return Length;
	}
	public void setLength(int length) {
		Length = length;
	}
	public char getCommandId() {
		return CommandId;
	}
	public void setCommandId(char commandId) {
		CommandId = commandId;
	}
	public int getContentLength() {
		return contentLength;
	}
	public void setContentLength(int contentLength) {
		this.contentLength = contentLength;
	}
	
}
