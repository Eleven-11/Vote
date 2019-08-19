package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 李文教
 * @date 2019/8/11 21:12
 */
@Data
public class Cat implements Serializable {
    private String oid ;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }
}
