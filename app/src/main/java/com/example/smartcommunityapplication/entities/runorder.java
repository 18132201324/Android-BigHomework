package com.example.smartcommunityapplication.entities;

public class runorder {
    private String  picture;
    private String theme;
    private String content;
    private String time;

    public runorder(String picture, String theme, String content, String time) {
        this.picture = picture;
        this.theme = theme;
        this.content = content;
        this.time = time;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
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
