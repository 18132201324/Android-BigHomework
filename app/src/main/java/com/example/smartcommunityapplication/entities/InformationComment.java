package com.example.smartcommunityapplication.entities;

public class InformationComment {
    private String userHeadImg;
    private String userName;
    private String user_id;
    private String InforMation_id;
    private String content;
    private String time;

    public InformationComment(){

    }

    public InformationComment(String userHeadImg, String userName, String user_id, String inforMation_id, String content, String time) {
        this.userHeadImg = userHeadImg;
        this.userName = userName;
        this.user_id = user_id;
        InforMation_id = inforMation_id;
        this.content = content;
        this.time = time;
    }

    public String getUserHeadImg() {
        return userHeadImg;
    }

    public void setUserHeadImg(String userHeadImg) {
        this.userHeadImg = userHeadImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getInforMation_id() {
        return InforMation_id;
    }

    public void setInfroMation_id(String infroMation_id) {
        InforMation_id = infroMation_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
