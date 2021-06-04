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
