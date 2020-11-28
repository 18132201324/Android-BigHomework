package com.example.smartcommunityapplication.entities;

public class Community {
    private String name;
    private String location;
    private String img;

    public Community(String name, String location, String img) {
        this.name = name;
        this.location = location;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
