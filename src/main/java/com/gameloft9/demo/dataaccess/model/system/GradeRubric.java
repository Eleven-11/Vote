package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李文教
 * @date 2019/7/17 17:20
 */
@Data
public class GradeRubric implements Serializable {
    //题目ID
    private int rubricId;
    //(外键)题目
    private int resId;
    //分数
    private String fraction;//
    //(外键)标题ID
    private String templetId;

    private List<GradeAnswer> answerList;
    private ResourceList resourceList;

    public int getRubricId() {
        return rubricId;
    }

    public void setRubricId(int rubricId) {
        this.rubricId = rubricId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public String getFraction() {
        return fraction;
    }

    public void setFraction(String fraction) {
        this.fraction = fraction;
    }

    public String getTempletId() {
        return templetId;
    }

    public void setTempletId(String templetId) {
        this.templetId = templetId;
    }

    public List<GradeAnswer> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<GradeAnswer> answerList) {
        this.answerList = answerList;
    }


}
