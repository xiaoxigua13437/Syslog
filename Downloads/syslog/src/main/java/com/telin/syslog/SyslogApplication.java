package com.telin.syslog;

import com.telin.syslog.server.SyslogClient;
import com.telin.syslog.server.SyslogServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class SyslogApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SyslogApplication.class);

	public static long count = 0L;


	public static void main(String[] args) {

		SpringApplication.run(SyslogApplication.class, args);

		LOGGER.info("-------当前版本为: 1.0.00-------");
		LOGGER.info("freeMemory:" + Runtime.getRuntime().freeMemory() / 1048576L + "M");
		LOGGER.info("maxMemory:" + Runtime.getRuntime().maxMemory() / 1048576L + "M");
		LOGGER.info("totalMemory:" + Runtime.getRuntime().totalMemory() / 1048576L + "M");

//		//初始化参数
//		InitializeParamConfig.configInitializeParam();
//		//开启定时任务
//		InitializeParamConfig.timeLoad();


//		String configUrl = InitializeParamConfig.getKey("configUrl", "/opt/telinSyslog/conf/config.xml");
//		String IpConfigUrl = InitializeParamConfig.getKey("IpConfigUrl", "/opt/telinSyslog/conf/IPconfig.xml");


		new Thread(new SyslogServer()).start();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}


		new Thread("b"){
			public void run(){
				int i = 0;
				while (true) {
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					Object obj = SyslogClient.sendData("192.168.44.120", 1813, "测试也可以");
				}
			}

		}.start();









		LOGGER.info("准备开始接收日志........");







	}

}

