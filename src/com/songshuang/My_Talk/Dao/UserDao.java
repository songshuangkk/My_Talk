package com.songshuang.My_Talk.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import io.netty.buffer.ByteBuf;

import javax.sql.DataSource;

import java.sql.PreparedStatement;

import com.songshuang.My_Talk.Manager.DataSoucrceManager;
import com.songshuang.My_Talk.Package.My_Package;

public class UserDao {
	
	private DataSource dataSource = null;
	private My_Package pack = null;
	
	public UserDao() throws Exception{
		setDataSource();
		pack = new My_Package();
	}
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource() throws Exception {
		this.dataSource = DataSoucrceManager.getInstance().getDataSource();
	}
	
	
	/* *
	 * 
	 * 获得当前在线人数
	 * 
	 * */
	
	public String SelectUser() throws Exception{
		String userList = "";
		int i = 1;
		String sql = "SELECT name FROM user_info JOIN login WHERE user_info.id = login.loginid;";
		Connection conn = dataSource.getConnection();
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rset =  pstm.executeQuery();
		while(rset.next()){
			userList = userList + i + "."  + rset.getString(1) + "\n";
			i++;
		}
		return pack.Filtration(userList);
	}

}
