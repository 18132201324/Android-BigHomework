package com.example.smartcommunityapplication.entities;

public class runorderinfoBean {
    private int picture;
    private String time;
    private String content;
    private String price;

    public runorderinfoBean(int picture, String time, String content, String price) {
        this.picture = picture;
        this.time = time;
        this.content = content;
        this.price = price;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
