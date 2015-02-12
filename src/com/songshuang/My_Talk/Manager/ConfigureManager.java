package com.songshuang.My_Talk.Manager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.songshuang.My_Talk.Configure.Configure;

public class ConfigureManager {
	
	private static ConfigureManager _configureManagerInstance = null;
	
	private Configure configure;
	
	public Configure getConfigure() {
		return configure;
	}

	public void setConfigure(Configure configure) {
		this.configure = configure;
	}

	public static ConfigureManager getInstance(){
		
		if(_configureManagerInstance == null)
		{
			_configureManagerInstance = new ConfigureManager();
		}
		
		return _configureManagerInstance;
	}
	
	/**
	 * name     : ConfigureManager
	 * params   : null
	 * function : 通过构造方法直接去构造配置对象
	 *
	 */
	public ConfigureManager(){
		configure = new Configure();
	}
	
	
	/**
	 * name     : loadAllConfigs
	 * params   : null
	 * function : 装载配置
	 * @throws Exception
	 */
	public void loadAllConfigs(){
		configure.loadConfigs();
	}

}
