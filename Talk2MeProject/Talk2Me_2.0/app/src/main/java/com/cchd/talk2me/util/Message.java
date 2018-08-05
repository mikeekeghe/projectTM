package com.cchd.talk2me.util;

import java.util.Date;


/**
 * Created by Eche Michael on 27/05/18.
 */
public class Message {
    private String mText;
    private String mSender;
    private String mRecipient;
    private Date mDate;

    public Date getDate() {
        return mDate;
    }


    public Message(String mText, String mSender, String mRecipient, Date mDate) {
        this.mText = mText;
        this.mSender = mSender;
        this.mRecipient = mRecipient;
        this.mDate = mDate;
    }

    public Message() {
    }

    public String getmRecipient() {
        return mRecipient;
    }

    public void setmRecipient(String mRecipient) {
        this.mRecipient = mRecipient;
    }

    public void setDate(Date date) {
        mDate = date;
    }

    public String getText() {
        return mText;
    }

    public void setText(String text) {
        mText = text;
    }

    public String getSender() {
        return mSender;
    }

    public void setSender(String sender) {
        mSender = sender;
    }
}
