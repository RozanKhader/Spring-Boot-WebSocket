package com.javatechie.spring.ws.api.model;

public class Command {

    private String messageType;
    private String data;
    private long ak;
    private long trackingId;


    public Command(long ak, String messagetype, String data, long trackingId) {
        this.ak = ak;
        this.messageType = messagetype;
        this.data = data;
        this.trackingId=trackingId;
    }

    public Command() {
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTrackingId() {
        return trackingId;
    }

    public void setTrackingId(long trackingId) {
        this.trackingId = trackingId;
    }

    public long getAk() {
        return ak;
    }

    public void setAk(long ak) {
        this.ak = ak;
    }

    @Override
    public String toString() {
        return "Command{" +
                "messageType='" + messageType + '\'' +
                ", data='" + data + '\'' +
                ", ak=" + ak +
                '}';
    }
}
