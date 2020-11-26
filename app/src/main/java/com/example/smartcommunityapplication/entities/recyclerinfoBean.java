package com.example.smartcommunityapplication.entities;

public class recyclerinfoBean {
    private int picture;
    private String title;
    public recyclerinfoBean(int picture, String title){
        this.picture = picture;
        this.title = title;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
