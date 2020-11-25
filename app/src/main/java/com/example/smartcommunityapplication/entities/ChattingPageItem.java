package com.example.smartcommunityapplication.entities;

public class ChattingPageItem {
    private String photo;
    private String name;
    private String content;
    private String time;

    public ChattingPageItem(String photo, String name, String content, String time) {
        this.photo = photo;
        this.name = name;
        this.content = content;
        this.time = time;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
