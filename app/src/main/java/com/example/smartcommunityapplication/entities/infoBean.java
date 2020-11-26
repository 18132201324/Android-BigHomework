package com.example.smartcommunityapplication.entities;

public class infoBean {
    private int picture;
    private String text;
    public infoBean(int picture){
        this.picture = picture;
    }
    public infoBean(String text){
        this.text = text;
    }
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getPicture() {
        return picture;
    }

    public void setPicture(int picture) {
        this.picture = picture;
    }

}
