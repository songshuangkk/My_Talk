package com.songshuang.Test.Method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ClientChat {
	
	private static ClientChat  clientChat = null;
	private BufferedReader read = null;
	public ClientChat(){
		read = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static ClientChat getInstance(){
		if (clientChat == null){
			clientChat = new ClientChat();
		}
		return clientChat;
	}
	
	public String run() throws Exception{
		System.out.println("请选择你要选择的聊天功能:\n1.群聊\n2.单人聊天");
		return read.readLine();
	}
}
