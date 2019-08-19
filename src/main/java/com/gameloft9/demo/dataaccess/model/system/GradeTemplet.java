package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GradeTemplet implements Serializable {
    //标题ID
    private int templetId;
    //标题
    private String title;
    //投票说明
    private String explains;
    //投票人数
    private String peonum;

    private List<GradeEvaluat> evaluatList;
    private List<GradeRubric> rubrics;

    public List<GradeRubric> getRubrics() {
        return rubrics;
    }

    public void setRubrics(List<GradeRubric> rubrics) {
        this.rubrics = rubrics;
    }

    public int getTempletId() {
        return templetId;
    }

    public void setTempletId(int templetId) {
        this.templetId = templetId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getPeonum() {
        return peonum;
    }

    public void setPeonum(String peonum) {
        this.peonum = peonum;
    }

    public List<GradeEvaluat> getEvaluatList() {
        return evaluatList;
    }

    public void setEvaluatList(List<GradeEvaluat> evaluatList) {
        this.evaluatList = evaluatList;
    }
}
