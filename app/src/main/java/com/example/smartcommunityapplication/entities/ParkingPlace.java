package com.example.smartcommunityapplication.entities;

public class ParkingPlace {
    private  String pid;//所在车库id
    private String id;//车位id
    private String userid;//车位占有者姓名
    private int rows;//车位所在行数
    private int cols;//车位所在列数
    private int stat;//车库状态

    public ParkingPlace() {
    }

    public ParkingPlace(String pid, String id, String userid, int rows, int cols, int stat) {
        this.pid = pid;
        this.id = id;
        this.userid = userid;
        this.rows = rows;
        this.cols = cols;
        this.stat = stat;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getCols() {
        return cols;
    }

    public void setCols(int cols) {
        this.cols = cols;
    }

    public int getStat() {
        return stat;
    }

    public void setStat(int stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "ParkingPlace{" +
                "pid='" + pid + '\'' +
                ", id='" + id + '\'' +
                ", userid='" + userid + '\'' +
                ", rows=" + rows +
                ", cols=" + cols +
                ", stat=" + stat +
                '}';
    }
}
