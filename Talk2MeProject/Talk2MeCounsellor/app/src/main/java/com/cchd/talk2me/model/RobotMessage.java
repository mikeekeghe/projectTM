package com.cchd.talk2me.model;

public class RobotMessage {
    private String rmsgId;
    private String oWelcomeMessage;
    private String oWelcomeDate;
    private String uReplyMsg1;
    private String uReplyDate1;
    private String uReplyMsg2;
    private String uReplyDate2;
    private String uReplyMsg3;
    private String uReplyDate3;
    private String oReplyFollowUp1;
    private String oReplyFollowUpDate1;
    private String oReplyFollowUp2;
    private String oReplyFollowUpDate2;
    private String oReplyFollowUp3;
    private String oReplyFollowUpDate3;
    private String user_email;

    public RobotMessage() {
    }

    public RobotMessage(String user_email, String oWelcomeMessage, String oWelcomeDate,
                        String uReplyMsg1, String uReplyDate1, String uReplyMsg2, String uReplyDate2,
                        String uReplyMsg3, String uReplyDate3, String oReplyFollowUp1,
                        String oReplyFollowUpDate1, String oReplyFollowUp2, String oReplyFollowUpDate2,
                        String oReplyFollowUp3, String oReplyFollowUpDate3) {
        this.user_email = user_email;
        this.oWelcomeMessage = oWelcomeMessage;
        this.oWelcomeDate = oWelcomeDate;
        this.uReplyMsg1 = uReplyMsg1;
        this.uReplyDate1 = uReplyDate1;
        this.uReplyMsg2 = uReplyMsg2;
        this.uReplyDate2 = uReplyDate2;
        this.uReplyMsg3 = uReplyMsg3;
        this.uReplyDate3 = uReplyDate3;
        this.oReplyFollowUp1 = oReplyFollowUp1;
        this.oReplyFollowUpDate1 = oReplyFollowUpDate1;
        this.oReplyFollowUp2 = oReplyFollowUp2;
        this.oReplyFollowUpDate2 = oReplyFollowUpDate2;
        this.oReplyFollowUp3 = oReplyFollowUp3;
        this.oReplyFollowUpDate3 = oReplyFollowUpDate3;
    }

    public String getRmsgId() {
        return rmsgId;
    }

    public void setRmsgId(String rmsgId) {
        this.rmsgId = rmsgId;
    }

    public String getoWelcomeMessage() {
        return oWelcomeMessage;
    }

    public void setoWelcomeMessage(String oWelcomeMessage) {
        this.oWelcomeMessage = oWelcomeMessage;
    }

    public String getoWelcomeDate() {
        return oWelcomeDate;
    }

    public void setoWelcomeDate(String oWelcomeDate) {
        this.oWelcomeDate = oWelcomeDate;
    }

    public String getuReplyMsg1() {
        return uReplyMsg1;
    }

    public void setuReplyMsg1(String uReplyMsg1) {
        this.uReplyMsg1 = uReplyMsg1;
    }

    public String getuReplyDate1() {
        return uReplyDate1;
    }

    public void setuReplyDate1(String uReplyDate1) {
        this.uReplyDate1 = uReplyDate1;
    }

    public String getuReplyMsg2() {
        return uReplyMsg2;
    }

    public void setuReplyMsg2(String uReplyMsg2) {
        this.uReplyMsg2 = uReplyMsg2;
    }

    public String getuReplyDate2() {
        return uReplyDate2;
    }

    public void setuReplyDate2(String uReplyDate2) {
        this.uReplyDate2 = uReplyDate2;
    }

    public String getuReplyMsg3() {
        return uReplyMsg3;
    }

    public void setuReplyMsg3(String uReplyMsg3) {
        this.uReplyMsg3 = uReplyMsg3;
    }

    public String getuReplyDate3() {
        return uReplyDate3;
    }

    public void setuReplyDate3(String uReplyDate3) {
        this.uReplyDate3 = uReplyDate3;
    }

    public String getoReplyFollowUp1() {
        return oReplyFollowUp1;
    }

    public void setoReplyFollowUp1(String oReplyFollowUp1) {
        this.oReplyFollowUp1 = oReplyFollowUp1;
    }

    public String getoReplyFollowUpDate1() {
        return oReplyFollowUpDate1;
    }

    public void setoReplyFollowUpDate1(String oReplyFollowUpDate1) {
        this.oReplyFollowUpDate1 = oReplyFollowUpDate1;
    }

    public String getoReplyFollowUp2() {
        return oReplyFollowUp2;
    }

    public void setoReplyFollowUp2(String oReplyFollowUp2) {
        this.oReplyFollowUp2 = oReplyFollowUp2;
    }

    public String getoReplyFollowUpDate2() {
        return oReplyFollowUpDate2;
    }

    public void setoReplyFollowUpDate2(String oReplyFollowUpDate2) {
        this.oReplyFollowUpDate2 = oReplyFollowUpDate2;
    }

    public String getoReplyFollowUp3() {
        return oReplyFollowUp3;
    }

    public void setoReplyFollowUp3(String oReplyFollowUp3) {
        this.oReplyFollowUp3 = oReplyFollowUp3;
    }

    public String getoReplyFollowUpDate3() {
        return oReplyFollowUpDate3;
    }

    public void setoReplyFollowUpDate3(String oReplyFollowUpDate3) {
        this.oReplyFollowUpDate3 = oReplyFollowUpDate3;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }
}
