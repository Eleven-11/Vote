package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
@Data
public class TpPhotoUser implements Serializable {
    private String  uid;
    private String uname;
    private int sex;
    private int departmentId;
    private int classOfPosition;
    private int position;
    private int gradeOfJudge;
    private int category;

    public TpPhotoUser() {
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
