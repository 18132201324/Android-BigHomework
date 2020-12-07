package com.example.smartcommunityapplication.entities;

public class Notice {
    private String userHeadImg;
    private String userName;
    private String user_id;
    private String community_id;
    private String title;
    private String content;
    private String time;
    private String picture1;
    private String picture2;
    private String picture3;

    public Notice(){

    }

    public Notice(String userName, String user_id, String community_id, String title, String content, String time, String picture1, String picture2, String picture3) {
        this.userName = userName;
        this.user_id = user_id;
        this.community_id = community_id;
        this.title = title;
        this.content = content;
        this.time = time;
        this.picture1 = picture1;
        this.picture2 = picture2;
        this.picture3 = picture3;
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

    public String getCommunity_id() {
        return community_id;
    }

    public void setCommunity_id(String community_id) {
        this.community_id = community_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getPicture1() {
        return picture1;
    }

    public void setPicture1(String picture1) {
        this.picture1 = picture1;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }
}
