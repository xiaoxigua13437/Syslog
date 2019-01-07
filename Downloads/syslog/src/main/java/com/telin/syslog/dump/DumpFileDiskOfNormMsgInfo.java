//package com.telin.syslog.dump;
//
//
//import com.telin.syslog.config.InitializeParamConfig;
//import com.telin.syslog.domain.MessageInfo;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import java.io.File;
//import java.util.Date;
//
///**
// * Created by zhaofang on 2019/1/4.
// */
//public class DumpFileDiskOfNormMsgInfo implements Runnable{
//
//    private static final Logger logger = LoggerFactory.getLogger(DumpFileDiskOfNormMsgInfo.class);
//
//
//    private static String succFilePath = InitializeParamConfig.syslogFilePath + File.separator + "parseSucc" + File.separator;
//    private static String failFilePath = InitializeParamConfig.syslogFilePath + File.separator + "parseFailure" + File.separator;
//    private static DumpFileDiskOfNormMsgInfo instance = null;
//
//
//    private DumpFileDiskOfNormMsgInfo() {
//        this.dumpDesc = "转存日志到文件";
//    }
//
//    public static synchronized DumpFileDiskOfNormMsgInfo getInstance() {
//        if (instance == null)
//            instance = new DumpFileDiskOfNormMsgInfo();
//        return instance;
//    }
//
//    public void run()
//    {
//        while (true) {
//            MessageInfo message = getMessage();
//            try
//            {
//                if (message != null) {
//                    if (message.getVendor() == 1)
//                        message.setMessageFileName(FileUtils.getSystemFileName(message.getSysDate()));
//                    else
//                        message.setMessageFileName(FileUtils.getSystemFileName(message.getReceiveDate()));
//                    StringBuffer fileName = new StringBuffer();
//                    if ((message.getMessageType() != null) && (!"".equals(message.getMessageType()))) {
//                        fileName.append(succFilePath);
//                    } else {
//                        fileName.append(failFilePath);
//                        SystemAlarmUtil.addAlarm(new Date(), 100, message.getHostname(), "解析失败！");
//                    }
//                    fileName.append(message.getMessageFileName());
//                    writeToFile(message.toString(), fileName.toString());
//                    continue;
//                }waitTimeToWrite();
//            }
//            catch (Exception e) {
//                SystemAlarmUtil.addAlarm(new Date(), 110, message.getHostname(), "保存失败告警：" + DateUtil.format(new Date()));
//                logger.error("数据写入文件过程中出错!", e);
//            }
//        }
//    }
//
//
//}
