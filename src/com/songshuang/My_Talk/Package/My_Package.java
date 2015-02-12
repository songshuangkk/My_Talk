package com.songshuang.My_Talk.Package;

/* *
 * 
 * 该类主要是对服务端发送的数据进行一个过滤处理
 * 
 * */
public class My_Package {
	
	public My_Package(){
		
	}
	
	public String Filtration(String msg){
		msg = msg.replace("\n", "$!");
		return msg;
	}

}
