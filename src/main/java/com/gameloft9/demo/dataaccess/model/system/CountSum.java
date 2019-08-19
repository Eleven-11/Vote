package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 李文教
 * @date 2019/8/11 23:00
 */
@Data
public class CountSum implements Serializable {
    private int countId;
    private int uid;
    private int rubricId;

}
