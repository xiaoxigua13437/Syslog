package com.telin.syslog.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhaofang on 2019/1/4.
 */
public class StartServer {

    private static final Logger LOGGER = LoggerFactory.getLogger(StartServer.class);

    private static long count = 0L;


    public void initServer(){

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
        LOGGER.info("准备开始接收日志........");

    }



}
