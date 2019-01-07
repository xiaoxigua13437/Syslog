package com.telin.syslog.domain;

import com.telin.syslog.util.DateUtil;
import java.net.DatagramPacket;
import java.util.Date;

/**
 * Created by zhaofang on 2019/1/5.
 */
public class MessageInfo {

    private DatagramPacket datagramPacket = null;

    private long msgId;

    private int level;

    private String hostName;

    private String deviceName;

    private String messageType;

    private String messageContent;

    private String messageContentTable;

    private String messageFileName;

    private int vendor;//供应商

    private Date receviceDate;

    private long receiveTime;

    private String receiveTimeStr;

    private Date sysDate;

    private long sysTime;

    private String sysTimeStr;

    private String code;


    public MessageInfo(){

    }

    public MessageInfo(DatagramPacket packet , Date date){
        this.datagramPacket = packet;
        this.receviceDate = date;
        this.receiveTime = date.getTime();
        this.receiveTimeStr = DateUtil.format(date,DateUtil.FORMAT_FULL);
        this.hostName = packet.getAddress().getHostAddress();
        this.messageContent = new String(packet.getData()).trim();

    }


    public String toString(){
        String info =this.hostName + "|" + this.deviceName + "|" + this.level + "|"
                + this.receiveTimeStr + "|" + this.sysTimeStr + "|"
                + this.messageType + "|" + this.messageContent + "|" + this.code + "|";

        return info.replace("null" ,"");
    }


    public byte[] getByteArray(DatagramPacket datagramPacket){

        byte[] bytes = this.datagramPacket.getData();
        return bytes;

    }


    public DatagramPacket getDatagramPacket() {
        return datagramPacket;
    }

    public void setDatagramPacket(DatagramPacket datagramPacket) {
        this.datagramPacket = datagramPacket;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getMessageContentTable() {
        return messageContentTable;
    }

    public void setMessageContentTable(String messageContentTable) {
        this.messageContentTable = messageContentTable;
    }

    public String getMessageFileName() {
        return messageFileName;
    }

    public void setMessageFileName(String messageFileName) {
        this.messageFileName = messageFileName;
    }

    public int getVendor() {
        return vendor;
    }

    public void setVendor(int vendor) {
        this.vendor = vendor;
    }

    public Date getReceviceDate() {
        return receviceDate;
    }

    public void setReceviceDate(Date receviceDate) {
        this.receviceDate = receviceDate;
    }

    public long getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(long receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getReceiveTimeStr() {
        return receiveTimeStr;
    }

    public void setReceiveTimeStr(String receiveTimeStr) {
        this.receiveTimeStr = receiveTimeStr;
    }

    public String getSysTimeStr() {
        return sysTimeStr;
    }

    public void setSysTimeStr(String sysTimeStr) {
        this.sysTimeStr = sysTimeStr;
    }

    public Date getSysDate() {
        return sysDate;
    }

    public void setSysDate(Date sysDate) {
        this.sysDate = sysDate;
    }

    public long getSysTime() {
        return sysTime;
    }

    public void setSysTime(long sysTime) {
        this.sysTime = sysTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
