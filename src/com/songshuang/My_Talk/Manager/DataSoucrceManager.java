package com.songshuang.My_Talk.Manager;

import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class DataSoucrceManager {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSoucrceManager.class);
	
	private static DataSoucrceManager dataManager = null;
	
	private static Properties dbPropertires = null;
	
	private static DataSource dataSource = null;

	/* *
	 *  
	 *  function : 获取数据库连接配置
	 *  
	 * */
	public DataSoucrceManager() throws Exception{
		this.dbPropertires = ConfigureManager.getInstance().getConfigure().getDbConfig();
		/* 进行创建数据库连接池  */
		dataSource = DruidDataSourceFactory.createDataSource(this.dbPropertires);
	}
	
	public static DataSoucrceManager getInstance() throws Exception{
		if (dataManager == null){
			dataManager = new DataSoucrceManager();
		}
		return dataManager;
	}
	
	
	
	public static final DataSource getDataSource() throws Exception{
		 DataSource dataSource = null;  
		 dataSource = DruidDataSourceFactory.createDataSource(dbPropertires);
		 return dataSource;
	}

}
