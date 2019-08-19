package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import sun.security.pkcs11.P11Util;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
/*
评测打分记录
 */

@Data
public class GradeRecord implements Serializable {
    //记录ID
    private int recordId;
    //标题ID
    private int templetId;

    private  String templeteName;

    private  String resName;
    //发布时间
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date rTime;
    //状态ID
    private int resId;

    private GradeTemplet templet;
    private List<CountSum> countSums;

    public List<CountSum> getCountSums() {
        return countSums;
    }

    public void setCountSums(List<CountSum> countSums) {
        this.countSums = countSums;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getTempletId() {
        return templetId;
    }

    public void setTempletId(int templetId) {
        this.templetId = templetId;
    }

    public void setrTime(Date rTime) {
        this.rTime = rTime;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }


    public Date getrTime() {
        return rTime;
    }
}
