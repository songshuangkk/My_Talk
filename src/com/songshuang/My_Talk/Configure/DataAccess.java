package com.songshuang.My_Talk.Configure;

public class DataAccess {
	
	private static DataAccess access = null;
	
	public DataAccess(){
		
	}
	
	public static DataAccess getInstance(){
		if (access == null){
			access = new DataAccess();
		}
		return access;
	}

}
