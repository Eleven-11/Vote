package com.gameloft9.demo.dataaccess.model.tp;

import lombok.Data;

@Data
public class TpResourcelist {
    private int id;
    private String resKind;
    private int resNum;
    private String resName;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getResKind() {
        return resKind;
    }

    public void setResKind(String resKind) {
        this.resKind = resKind;
    }

    public int getResNum() {
        return resNum;
    }

    public void setResNum(int resNum) {
        this.resNum = resNum;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName;
    }

    public TpResourcelist() {
    }

    public TpResourcelist(int id, String resKind, int resNum, String resName) {
        this.id = id;
        this.resKind = resKind;
        this.resNum = resNum;
        this.resName = resName;
    }

    public TpResourcelist(String resKind, int resNum, String resName) {
        this.resKind = resKind;
        this.resNum = resNum;
        this.resName = resName;
    }
}
