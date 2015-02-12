package com.songshuang.My_Talk;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.songshuang.My_Talk.Configure.Sysconstants;
import com.songshuang.My_Talk.Manager.ConfigureManager;
import com.songshuang.My_Talk.Manager.NetWorkManager;

/* *
 * 初始化配置启动项目
 * 
 * @author songshuang
 * 
 * 
 * */
public class TalkMainServer {
	
	private static final Logger logger = LoggerFactory.getLogger(TalkMainServer.class);
	
	/* *
	 * name     : bootup
	 * params   : port
	 * return   : null
	 * function : 进行启动，开初始化工作
	 * 
	 * */
	public void bootup (int port){
		initLog();
		initConfigure();
		initBase();
		initNet(port);
	}
	
	
	
	/* *
	 * name     : initLog
	 * params   : null
	 * return   : null
	 * function : 初始化日志
	 * 
	 * */
	public void initLog() {
		// 这里好像没什么好做的...
	}
	
	
	
	/* *
	 * name     : initConfigure
	 * params   : null
	 * return   : null
	 * function : 初始化配置信息
	 * 
	 * */
	public void initConfigure(){
		ConfigureManager.getInstance().loadAllConfigs();
	}
	
	
	
	/* *
	 * name     : initBase
	 * params   : null
	 * return   : null
	 * function : 初始化数据库连接池
	 * 
	 * */
	public void initBase(){
	}
	
	
	
	/* *
	 * name     : initNet
	 * params   : null
	 * return   : null
	 * function : 初始化网络
	 * 
	 * */
	public void initNet(int port){
		NetWorkManager netWorkManager = NetWorkManager.getInstance();
		netWorkManager.init(port);
		// 消息分发器
		
	}
	
	public static void main(String[] args){
		int port = Sysconstants.SERVER_DEFAULT_PORT;
		TalkMainServer server = new TalkMainServer();
		server.bootup(port);
	}

	
}
