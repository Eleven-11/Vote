package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李文教
 * @date 2019/8/11 21:13
 */
@Data
public class Dog implements Serializable {
    private String uid;
    private List<Cat> catList;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<Cat> getCatList() {
        return catList;
    }

    public void setCatList(List<Cat> catList) {
        this.catList = catList;
    }
}
