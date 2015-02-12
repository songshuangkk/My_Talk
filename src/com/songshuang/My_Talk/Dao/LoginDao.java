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
	
	static int loginId;		// ������¼�û���ID��

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource() throws Exception {
		this.dataSource = DataSoucrceManager.getInstance().getDataSource();
	}
	
	/* *
	 * 
	 * function : �������ݿ��ѯ���鿴�Ƿ����Ѿ�ע����û�
	 * 
	 * */
	
	public boolean checkLogin(String userName, String passWord, String channelId) throws Exception{
		this.setDataSource(); // �����ݿ����ӽ��г�ʼ��
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
				String InsertSql = "INSERT INTO login VALUES("+ loginId +",'"+date+"','"+channelId+"')" + ";";	// ���û��ĵ����¼���뵽�������,��ȡ���û����û���
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
			return true;	// ��־ִ�гɹ�
		}
		conn.close();
		pstm.close();
		return false;
	}

}
