package com.songshuang.My_Talk.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.songshuang.My_Talk.Manager.DataSoucrceManager;

public class RegisterDao {
	
	private DataSource dataSource;
	
	public DataSource getDataSource() {
		return dataSource;
	}
	
	public void setDataSource() throws Exception {
		this.dataSource = DataSoucrceManager.getInstance().getDataSource();
	}
	
	public boolean registerUser(String name, String password) throws Exception{
		this.setDataSource();
		String sql = "INSERT INTO user_info (name, password) VALUES('"+name+"','"+password+"')";
		Connection conn = this.dataSource.getConnection();
		PreparedStatement pstm = conn.prepareStatement(sql);
		if (pstm.executeUpdate() > 0){
			pstm.close();
			conn.close();
			return true;	// 标志执行成功
		}
		conn.close();
		pstm.close();
		return false;
		
	}

}
