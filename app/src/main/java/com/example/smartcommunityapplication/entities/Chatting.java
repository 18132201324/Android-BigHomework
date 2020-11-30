package com.example.smartcommunityapplication.entities;

public class Chatting {
    private int id;
    private String fromName;
    private String toName;
    private String content;
    private String time;

    public Chatting(int id,String fromName, String toName, String content, String time) {
        this.id=id;
        this.fromName = fromName;
        this.toName = toName;
        this.content = content;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getToName() {
        return toName;
    }

    public void setToName(String toName) {
        this.toName = toName;
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
