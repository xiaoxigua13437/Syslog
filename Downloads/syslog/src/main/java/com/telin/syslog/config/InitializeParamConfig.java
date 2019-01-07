package com.telin.syslog.config;

import org.jooq.tools.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by zhaofang on 2019/1/4.
 * 初始化参数
 */
public class InitializeParamConfig {

    public static String IP;
    public static int PORT;
    public static int BUFFERSIZE;
    public static int MESSAGEBUFFERSIZE;
    private static Properties properties = new Properties();
    public static String syslogFilePath;
    public static String isH3CProcess;
    public static int batchInterval;
    private static final Logger logger = LoggerFactory.getLogger(InitializeParamConfig.class);

    public static void configInitializeParam()
    {
        logger.info("加载初始化参数...");
        try {
            File f = new File("conf", "initparam.properties");
            if (f.exists())
            {
                configProperties(f);
                checkInvalidParam();
            } else {
                logger.error("当前目录下没有conf/initparam.properties文件，请检查.程序退出...");
                System.exit(0);
            }
        } catch (Exception e) {
            logger.error("初始化配置文件initparam.properties错误,程序退出!", e);
            System.exit(0);
        }
    }

    private static void configProperties(File file) throws IOException
    {
        properties.load(new FileInputStream(file));
        IP = getKey("ip", null);
        PORT = getKey("port", 514);
        BUFFERSIZE = getKey("bufferSize", 2048);
        MESSAGEBUFFERSIZE = getKey("messageBufferSize", 50000);
        syslogFilePath = getKey("syslogFilePath", "/opt/syslog/");
        batchInterval = getKey("batchInterval", 10);
    }

    private static void checkInvalidParam()
    {
        boolean isNoVendorProcess = true;

        String defaultVal = "2";
        isH3CProcess = getKey("isH3CProcess", defaultVal);
        if (isH3CProcess.equals("1"))
        {
            isNoVendorProcess = false;
        }

        if (isNoVendorProcess)
            logger.info("没有开启任何厂商日志解析,所有日志都会解析失败，请注意！！！");
    }

    public static String getKey(String key)
    {
        String k = properties.getProperty(key);
        if (k != null) {
            return k;
        }
        logger.info("读取initparam.properties文件中的" + key + "失败,将采用默认配置");
        return null;
    }

    public static String getKey(String key, String defaultVal)
    {
        String k = getKey(key);
        if (k == null) return defaultVal;
        return k;
    }

    public static long getKey(String key, long defaultVal) {
        String k = getKey(key);
        if (StringUtils.isBlank(k)) {
            return defaultVal;
        }
        return Long.valueOf(k).longValue();
    }

    public static int getKey(String key, int defaultVal)
    {
        String k = getKey(key);
        if (StringUtils.isBlank(k)) {
            return defaultVal;
        }
        return Integer.valueOf(k).intValue();
    }

    public static void timeLoad()
    {
        logger.info("启动定时加载配置文件任务");
        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask()
                              {
                                  public void run() {
                                      try {
                                          File f = new File("conf", "initparam.properties");
                                          if (f.exists()) {
//                                              InitializeParamConfig.access$0(f);
//                                              InitializeParamConfig.access$1();
                                          } else {
                                              InitializeParamConfig.logger.error("当前目录下没有配置文件，请检查，暂时采用原有配置");
//                                              if ("1".equals(InitializeParamConfig.getKey("mailAlarm")))
//                                                  EmailUtil.sendErrorMail("IP:" + InitializeParamConfig.IP + System.getProperty("line.separator") + "定时加载配置文件的时候发现配置文件initparam.properties不存在。", "定时扫描配置文件错误", "定时扫描配置文件");
                                         }
                                      }
                                      catch (Exception e) {
                                          InitializeParamConfig.logger.error("资源文件读取出错:", e);
                                      }
                                  }
                              }
                , 600000L, 3600000L);
    }


}

















