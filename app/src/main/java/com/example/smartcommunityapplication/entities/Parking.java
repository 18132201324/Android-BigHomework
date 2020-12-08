package com.example.smartcommunityapplication.entities;

public class Parking {
    private String pid;//车库编号
    private String paddress;//车库地址
    private int prows;//车库行数
    private int pcols;//车库列数

    public Parking() {
    }

    public Parking(String pid, String paddress, int prows, int pcols) {
        this.pid = pid;
        this.paddress = paddress;
        this.prows = prows;
        this.pcols = pcols;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPaddress() {
        return paddress;
    }

    public void setPaddress(String paddress) {
        this.paddress = paddress;
    }

    public int getProws() {
        return prows;
    }

    public void setProws(int prows) {
        this.prows = prows;
    }

    public int getPcols() {
        return pcols;
    }

    public void setPcols(int pcols) {
        this.pcols = pcols;
    }

    @Override
    public String toString() {
        return "Parking{" +
                "pid=" + pid +
                ", paddress='" + paddress + '\'' +
                ", prows=" + prows +
                ", pcols=" + pcols +
                '}';
    }
}
