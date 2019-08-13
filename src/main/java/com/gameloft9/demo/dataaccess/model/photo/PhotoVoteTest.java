package com.gameloft9.demo.dataaccess.model.photo;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 年: 2019
 * 月: 07
 * 日: 11
 * 小时: 21
 * 分钟: 03
 *
 * @author 严脱兔
 */
@Data
public class PhotoVoteTest implements Serializable{
    private int vid;
    private String title;
    private String modelTitle;
    private String votingInstruction;
    private int votedNum;
    //List<TestDemo> list = new ArrayList<TestDemo>();
    private  List<PhotoTitleTest> list;
    private List<String> accounts;
}




