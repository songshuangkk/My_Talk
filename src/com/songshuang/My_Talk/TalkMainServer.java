package com.songshuang.My_Talk;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.songshuang.My_Talk.Configure.Sysconstants;
import com.songshuang.My_Talk.Manager.ConfigureManager;
import com.songshuang.My_Talk.Manager.NetWorkManager;

/* *
 * ��ʼ������������Ŀ
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
	 * function : ��������������ʼ������
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
	 * function : ��ʼ����־
	 * 
	 * */
	public void initLog() {
		// �������ûʲô������...
	}
	
	
	
	/* *
	 * name     : initConfigure
	 * params   : null
	 * return   : null
	 * function : ��ʼ��������Ϣ
	 * 
	 * */
	public void initConfigure(){
		ConfigureManager.getInstance().loadAllConfigs();
	}
	
	
	
	/* *
	 * name     : initBase
	 * params   : null
	 * return   : null
	 * function : ��ʼ�����ݿ����ӳ�
	 * 
	 * */
	public void initBase(){
	}
	
	
	
	/* *
	 * name     : initNet
	 * params   : null
	 * return   : null
	 * function : ��ʼ������
	 * 
	 * */
	public void initNet(int port){
		NetWorkManager netWorkManager = NetWorkManager.getInstance();
		netWorkManager.init(port);
		// ��Ϣ�ַ���
		
	}
	
	public static void main(String[] args){
		int port = Sysconstants.SERVER_DEFAULT_PORT;
		TalkMainServer server = new TalkMainServer();
		server.bootup(port);
	}

	
}
