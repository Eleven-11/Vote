package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/*
人员管理
 */
@Data
public class PhotoUser implements Serializable {
    //人员ID
    private int uid;
    //姓名
    private String uname;
    //性别
    private int sex;
    //部门
    private int departmentId;
    //职务
    private int classOfPosition;
    //职级
    private String position;
    //法官等级
    private int gradeOfudge;
    //所属类别
    private int category;




}
