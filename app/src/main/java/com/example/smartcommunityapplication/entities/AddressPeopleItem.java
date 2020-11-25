package com.example.smartcommunityapplication.entities;

public class AddressPeopleItem {
    private String photo;
    private String name;

    public AddressPeopleItem(String photo, String name) {
        this.photo = photo;
        this.name = name;
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
}
