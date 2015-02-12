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
	 * function : ͨ�����췽��ֱ��ȥ�������ö���
	 *
	 */
	public ConfigureManager(){
		configure = new Configure();
	}
	
	
	/**
	 * name     : loadAllConfigs
	 * params   : null
	 * function : װ������
	 * @throws Exception
	 */
	public void loadAllConfigs(){
		configure.loadConfigs();
	}

}
