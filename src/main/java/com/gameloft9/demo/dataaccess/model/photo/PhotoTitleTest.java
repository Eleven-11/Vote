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

}
