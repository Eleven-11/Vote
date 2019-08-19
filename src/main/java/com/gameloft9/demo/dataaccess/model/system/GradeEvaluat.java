package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李文教
 * @date 2019/7/17 17:23
 */
@Data
public class GradeEvaluat implements Serializable {
    private int evaluatId;
    //被测人员Id(外键)
    private int uid;
    //标题ID(外键)
    private int templetId;

    private List<PhotoUser> userList;

    public List<PhotoUser> getUserList() {
        return userList;
    }

    public void setUserList(List<PhotoUser> userList) {
        this.userList = userList;
    }

    public int getEvaluatId() {
        return evaluatId;
    }

    public void setEvaluatId(int evaluatId) {
        this.evaluatId = evaluatId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getTempletId() {
        return templetId;
    }

    public void setTempletId(int templetId) {
        this.templetId = templetId;
    }
}
