package com.songshuang.My_Talk.Package;

/* *
 * 
 * ������Ҫ�ǶԷ���˷��͵����ݽ���һ�����˴���
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
