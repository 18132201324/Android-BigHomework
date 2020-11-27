package com.example.smartcommunityapplication.entities;

import android.widget.ImageView;

public class Second_shop {
    private String shopId;//id
    private String shopImage;//店铺头像照片
    private String shopName;//店铺名称
    private String shopPointx;//店铺x坐标
    private String shopPointy;//店铺y坐标
    private String shopCategory;//店铺类别
    private String shopTel;//店铺电话
    private String shopAddress;//店铺地址
    private String shopsCenery1;//店铺风景图片1
    private String shopsCenery2;//店铺风景图片2

    public Second_shop() {
    }

    public Second_shop(String shopId, String shopImage, String shopName, String shopPointx, String shopPointy, String shopCategory, String shopTel, String shopAddress, String shopsCenery1, String shopsCenery2) {
        this.shopId = shopId;
        this.shopImage = shopImage;
        this.shopName = shopName;
        this.shopPointx = shopPointx;
        this.shopPointy = shopPointy;
        this.shopCategory = shopCategory;
        this.shopTel = shopTel;
        this.shopAddress = shopAddress;
        this.shopsCenery1 = shopsCenery1;
        this.shopsCenery2 = shopsCenery2;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }

    public String getShopImage() {
        return shopImage;
    }

    public void setShopImage(String shopImage) {
        this.shopImage = shopImage;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopPointx() {
        return shopPointx;
    }

    public void setShopPointx(String shopPointx) {
        this.shopPointx = shopPointx;
    }

    public String getShopPointy() {
        return shopPointy;
    }

    public void setShopPointy(String shopPointy) {
        this.shopPointy = shopPointy;
    }

    public String getShopCategory() {
        return shopCategory;
    }

    public void setShopCategory(String shopCategory) {
        this.shopCategory = shopCategory;
    }

    public String getShopTel() {
        return shopTel;
    }

    public void setShopTel(String shopTel) {
        this.shopTel = shopTel;
    }

    public String getShopAddress() {
        return shopAddress;
    }

    public void setShopAddress(String shopAddress) {
        this.shopAddress = shopAddress;
    }

    public String getShopsCenery1() {
        return shopsCenery1;
    }

    public void setShopsCenery1(String shopsCenery1) {
        this.shopsCenery1 = shopsCenery1;
    }

    public String getShopsCenery2() {
        return shopsCenery2;
    }

    public void setShopsCenery2(String shopsCenery2) {
        this.shopsCenery2 = shopsCenery2;
    }

    //ToString 方法
    @Override
    public String toString() {
        return "second_shop{" +
                "shopId='" + shopId + '\'' +
                ", shopImage='" + shopImage + '\'' +
                ", shopName='" + shopName + '\'' +
                ", shopPointx='" + shopPointx + '\'' +
                ", shopPointy='" + shopPointy + '\'' +
                ", shopCategory='" + shopCategory + '\'' +
                ", shopTel='" + shopTel + '\'' +
                ", shopAddress='" + shopAddress + '\'' +
                ", shopsCenery1='" + shopsCenery1 + '\'' +
                ", shopsCenery2='" + shopsCenery2 + '\'' +
                '}';
    }
}
