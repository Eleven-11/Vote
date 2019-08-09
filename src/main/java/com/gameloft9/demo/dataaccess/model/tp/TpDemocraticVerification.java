package com.gameloft9.demo.dataaccess.model.tp;

import java.util.List;

public class TpDemocraticVerification {
    private int yid;
    private int id;
    private String content;
    private int recordId;
    private int isVote;
    private List<String> num;

    public TpDemocraticVerification() {
    }

    public TpDemocraticVerification(int id, String content, int isVote) {
        this.id = id;
        this.content = content;
        this.isVote = isVote;
    }

    public TpDemocraticVerification(int id, String content, int recordId, int isVote) {
        this.id = id;
        this.content = content;
        this.recordId = recordId;
        this.isVote = isVote;
    }

    public List<String> getNum() {
        return num;
    }

    public void setNum(List<String> num) {
        this.num = num;
    }

    public int getYid() {
        return yid;
    }

    public void setYid(int yid) {
        this.yid = yid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getIsVote() {
        return isVote;
    }

    public void setIsVote(int isVote) {
        this.isVote = isVote;
    }
}
