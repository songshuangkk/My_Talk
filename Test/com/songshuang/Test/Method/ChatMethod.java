package com.songshuang.Test.Method;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ChatMethod {
	private BufferedReader read = null;
	
	private static ChatMethod chatMethod = null;
	
	public ChatMethod(){
		new BufferedReader(new InputStreamReader(System.in));
	}
	
	public static ChatMethod getInstance(){
		if (chatMethod == null){
			chatMethod = new ChatMethod();
		}
		return chatMethod;
	}
	
	public String run() throws Exception{
		
		return read.readLine();
	}

}
