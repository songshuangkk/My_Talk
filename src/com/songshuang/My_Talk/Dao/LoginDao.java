package com.songshuang.My_Talk.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sql.DataSource;

import com.songshuang.My_Talk.Manager.DataSoucrceManager;

public class LoginDao {
	
	private static DataSource dataSource;
	
	static int loginId;		// 用来记录用户的ID号

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource() throws Exception {
		this.dataSource = DataSoucrceManager.getInstance().getDataSource();
	}
	
	/* *
	 * 
	 * function : 进行数据库查询，查看是否是已经注册的用户
	 * 
	 * */
	
	public boolean checkLogin(String userName, String passWord, String channelId) throws Exception{
		this.setDataSource(); // 对数据库连接进行初始化
		String sql = "SELECT * FROM user_info WHERE user_info.name='"+userName+"' AND user_info.password='"+passWord+"';";
		// sql = "SELECT * FROM user_info;";
		Connection conn = dataSource.getConnection();
		PreparedStatement pstm = conn.prepareStatement(sql);
		ResultSet rset = pstm.executeQuery();
		if (rset.next()){
			System.out.println(rset.getString(2));
			if (rset.getString(2).equals(userName)){
				loginId = rset.getInt(1);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Date now = new Date();
				String date = sdf.format(now);
				String InsertSql = "INSERT INTO login VALUES("+ loginId +",'"+date+"','"+channelId+"')" + ";";	// 将用户的登入记录插入到登入表中,并取得用户的用户号
				pstm.execute(InsertSql);
				return true;
			}
		}
		return false;
	}
	
	
	public boolean SignOut() throws Exception{
		String sql = "DELETE FROM login WHERE loginid = "+loginId+";"; 
		Connection conn = dataSource.getConnection();
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
