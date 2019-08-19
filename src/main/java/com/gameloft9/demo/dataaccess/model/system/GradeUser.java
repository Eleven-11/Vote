package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 李文教
 * @date 2019/7/14 16:54
 */
@Data
public class GradeUser implements Serializable {

    private String username;

    private int uid;
    //姓名
    private String uname;
    //用户角色
    private String role;

    private String tel;

    private String password;



}
