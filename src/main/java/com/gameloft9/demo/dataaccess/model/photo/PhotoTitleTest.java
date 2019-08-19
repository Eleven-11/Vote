package com.gameloft9.demo.dataaccess.model.photo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 年: 2019
 * 月: 07
 * 日: 17
 * 小时: 09
 * 分钟: 20
 *
 * @author 严脱兔
 */
@Data
public class PhotoTitleTest implements Serializable {
    private int tid ;
    private int vid;
    private List<PhotoContentTest> optionTitle;
    private int optionRule;
    private int isChar;
    private List<PhotoAnswerTest> answerList;


//    private List<PhotoContentTest> contentList;
//    List<PhotoAnswerTest> list = new ArrayList<PhotoAnswerTest>();
//    List<PhotoContentTest>list1= new ArrayList<PhotoContentTest>();


    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getVid() {
        return vid;
    }

    public void setVid(int vid) {
        this.vid = vid;
    }

    public List<PhotoContentTest> getOptionTitle() {
        return optionTitle;
    }

    public void setOptionTitle(List<PhotoContentTest> optionTitle) {
        this.optionTitle = optionTitle;
    }

    public int getOptionRule() {
        return optionRule;
    }

    public void setOptionRule(int optionRule) {
        this.optionRule = optionRule;
    }

    public int getIsChar() {
        return isChar;
    }

    public void setIsChar(int isChar) {
        this.isChar = isChar;
    }

    public List<PhotoAnswerTest> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<PhotoAnswerTest> answerList) {
        this.answerList = answerList;
    }
}
