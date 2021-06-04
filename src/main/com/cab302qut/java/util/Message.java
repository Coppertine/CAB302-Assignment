package com.cab302qut.java.util;

import java.io.Serializable;

/**
 * Class to create a 'Message' object to use in
 * transmissions to detail the message type e.g. Login, CheckTrades
 * and the related object 'messageObject'
 *
 * @author Giane
 */
public class Message implements Serializable {
    private String messageType;
    private Object messageObject;

    /***
     * Constructor for simple messages like status check
     * @param msgtype The message type operation
     */
    public Message(String msgtype) {
        this.messageType = msgtype;
    }

    /**
     * Constructor for message with an associated object
     * @param msgType The message type operation
     * @param msgObj The object to send
     */
    public Message(String msgType, Object msgObj) {
        this.messageType = msgType;
        this.messageObject = msgObj;
    }

    public String getMessageType(){
        return messageType;
    }

    public Object getMessageObject() {
        // cast the object to the desired object after returning
        return messageObject;
    }
}
