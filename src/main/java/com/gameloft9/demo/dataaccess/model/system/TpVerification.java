package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

//验证码表
@Data
public class TpVerification{
    private int yid;
    private String content;
    private int recordId;
    private String isVote;
    private int id;

    public TpVerification() {
    }

    public TpVerification(String content, int recordId, String isVote, int id) {
        this.content = content;
        this.recordId = recordId;
        this.isVote = isVote;
        this.id = id;
    }

    public int getYid() {
        return yid;
    }

    public void setYid(int yid) {
        this.yid = yid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String getIsVote() {
        return isVote;
    }

    public void setIsVote(String isVote) {
        this.isVote = isVote;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
