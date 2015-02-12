package com.songshuang.Test.Package;

public class TestPackage {
	
	public TestPackage(){
		
	}
	
	public String Resolve(String msg){
		return msg.replace("$!", "\n");
	}

}
