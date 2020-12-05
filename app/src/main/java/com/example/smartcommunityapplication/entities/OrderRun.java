package com.example.smartcommunityapplication.entities;

import java.io.Serializable;

public class OrderRun implements Serializable {
    private String id;
    private String user_id;
    private String type;
    private String title;
    private float money;
    private int isconsult;
    private String time_start;
    private String time_stop;
    private String content;

    public OrderRun(){

    }
    public OrderRun(String id, String user_id, String type,String title, float money, int isconsult, String time_start, String time_stop, String content) {
        this.id = id;
        this.user_id = user_id;
        this.type = type;
        this.title = title;
        this.money = money;
        this.isconsult = isconsult;
        this.time_start = time_start;
        this.time_stop = time_stop;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public int getIsconsult() {
        return isconsult;
    }

    public void setIsconsult(int isconsult) {
        this.isconsult = isconsult;
    }

    public String getTime_start() {
        return time_start;
    }

    public void setTime_start(String time_start) {
        this.time_start = time_start;
    }

    public String getTime_stop() {
        return time_stop;
    }

    public void setTime_stop(String time_stop) {
        this.time_stop = time_stop;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
