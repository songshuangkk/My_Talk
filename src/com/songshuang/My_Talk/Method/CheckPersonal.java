package com.songshuang.My_Talk.Method;

import java.sql.SQLException;

import com.songshuang.My_Talk.Dao.LoginDao;

public class CheckPersonal {
	
	static CheckPersonal checkPersonal = null;
	private String name = null;
	private String password = null;
	
	private LoginDao login = null;
	
	public  CheckPersonal(){
		
	}
	
	public static CheckPersonal getInstance(){
		if (checkPersonal == null){
			checkPersonal = new CheckPersonal();
		} 
		return checkPersonal;
	}
	
	
	/* *
	 * 
	 * function : �������ݿ�ķ����ж��û�������û����������Ƿ���ȷ
	 * 
	 * */
	public boolean check(String name, String password) throws Exception{
		this.name = name;
		this.password = password;
		this.login = new LoginDao();
		// return login.checkLogin(name, password);
		return false;
	}

}
