package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
/*
答案表
 */

@Data
public class GradeAnswer implements Serializable {
    //答案ID
    private int answerId;
    //答案(外键)
    private int resId;
    //题目ID(外键)
    private int rubricId;
    //分值
    private String price;


    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }

    public int getRubricId() {
        return rubricId;
    }

    public void setRubricId(int rubricId) {
        this.rubricId = rubricId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }



}
