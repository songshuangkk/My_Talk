package com.songshuang.My_Talk.Configure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Configure {
	
	public static final Logger logger = LoggerFactory.getLogger(Configure.class);
	
	public Properties systemConfig;
	public Properties dbConfig;
	
	public Properties getDbConfig() {
		return dbConfig;
	}

	public void setDbConfig(Properties dbConfig) {
		this.dbConfig = dbConfig;
	}

	public Properties getSystemConfig() {
		return systemConfig;
	}

	public void setSystemConfig(Properties systemConfig) {
		this.systemConfig = systemConfig;
	}
	
	public Configure(){
		this.systemConfig = new Properties();
		this.dbConfig 	  = new Properties();
	}
	
	/**
	 * name     : loadConfigs
	 * params   : null
	 * function : 装载配置项条目
	 *
	 */
	public void loadConfigs(){
		loadSystemConfig(); // 系统配置
		loadDBConfig(); 	// 数据库连接配置
		loadCacheConfig(); 
		loadActionConfig(); // action相关的路由配置
		// loadTimerConfig(); // 计划任务脚本配置	
	}
	
	public void propertyConfigLoader_sys(String configFilePath,  Properties config){
		InputStream configFileStream = this.getClass().getResourceAsStream(configFilePath);
		try{
			logger.info("load " + configFilePath);
			config.load(configFileStream);
			configFileStream.close();
		} catch (IOException e){
			logger.error("", e);
		}
	}
	
	/**
	 * name     : loadSystemConfig
	 * params   : null
	 * function :系统配置
	 *
	 */
	public void loadSystemConfig(){
		propertyConfigLoader_sys("/System.properties", systemConfig);
	}

	/**
	 * name     : loadDBConfig
	 * params   : null
	 * function : 主要是对数据库连接池的配置
	 *
	 */
	public void loadDBConfig(){
		propertyConfigLoader_sys("/druid.properties", dbConfig);
	}
	
	public void loadCacheConfig(){}
	
	public void loadActionConfig(){}
}
