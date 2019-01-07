package com.telin.syslog.server;

import com.telin.syslog.SyslogApplication;
import com.telin.syslog.config.InitializeParamConfig;
import com.telin.syslog.domain.MessageInfo;
import com.telin.syslog.util.PattenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.*;
import java.util.Date;

/**
 * Created by zhaofang on 2019/1/4.
 */
public class SyslogServer implements Runnable {


    private static final Logger logger = LoggerFactory.getLogger(SyslogServer.class);

    private DatagramSocket serverSocket;



    public SyslogServer() {

        registerParser();

        startThreadOfParse();

        startThreadOfNormDump();

        initServerSocket();

    }


    private void registerParser() {

        if ("1".equals(InitializeParamConfig.isH3CProcess)) {

        }


    }

    /**
     * 开启解析线程
     */
    private void startThreadOfParse() {

        int parseThreadCount = InitializeParamConfig.getKey("parseThreadCount", 1);

        try {
            for ( int i = 0; i <= parseThreadCount; i++ ) {
                this.logger.info("开启第" + i + "个日志解析器...");
                Thread t1 = new Thread();
                t1.setName(" receive data thread [" + i + "]");
                t1.start();
            }

            this.logger.info("将启动" + parseThreadCount + "个解析线程.");
        } catch (Exception e) {
            this.logger.info("启动解析线程异常", e);
        }
    }

    /**
     * 开启写文件线程
     */
    private void startThreadOfNormDump() {

        if ("1".equals(InitializeParamConfig.getKey("isWriterToFile", "1"))) {
            this.logger.info("开启写文件线程");
        }

        try {



        }catch (Exception e){


        }






    }

    /**
     * 初始化socket
     */
    private void initServerSocket() {

        try {
            //睡眠5秒
            Thread.sleep(5000L);

            if (InitializeParamConfig.IP != null) {
                InetAddress address = InetAddress.getByName(InitializeParamConfig.IP);
                this.serverSocket = new DatagramSocket(InitializeParamConfig.PORT, address);

            } else {

                this.logger.info("ip地址未配置,自动获取ip地址" + InetAddress.getLocalHost().getHostAddress());
                this.serverSocket = new DatagramSocket(1813, InetAddress.getLocalHost());
            }

        } catch (Exception e) {
            this.logger.error("不能创建DatagramSocket,请检查端口号或者ip地址" + e.getMessage());
            this.logger.info("程序退出!");
            System.exit(0);
        }

    }

    @Override
    public void run() {

        try {
            this.logger.info("default bufferSize:" + this.serverSocket.getReceiveBufferSize());
            this.serverSocket.setReceiveBufferSize(4194304);
            this.logger.info("new bufferSize:" + this.serverSocket.getReceiveBufferSize());
        } catch (SocketException e) {
            this.logger.error("set udp buffersize error!");
        }

        DatagramPacket datagramPacket = null;
        byte[] buff = new byte[0];//声明一个长度0的数组,防止空指针异常
        while (true) {
            try {
                buff = new byte[1024 * 16];
                datagramPacket = new DatagramPacket(buff, buff.length);
                this.serverSocket.receive(datagramPacket);

                //解析接收到数据包
                byte[] bf = datagramPacket.getData();
                String dataResult = new String(bf);

                logger.info("解析接收的数据包内容:" + dataResult.toString());

                //数据包封装
                MessageInfo message = new MessageInfo(datagramPacket,new Date());

            } catch (Exception e) {
                this.logger.error("接收数据包错误!" + e.getMessage());
            }

            SyslogApplication.count = +1L;

        }

    }

    public void serverClose(){
        this.serverSocket.close();
    }





}
