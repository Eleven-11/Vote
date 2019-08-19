package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 李文教
 * @date 2019/7/23 11:14
 */
@Data
public class GradeAdm implements Serializable {
    private int admId;
    private int templetId;

    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    private Date aTime;
    
    private String templetTitle;

    private int resId;

    private GradeTemplet templet;

    public int getAdmId() {
        return admId;
    }

    public void setAdmId(int admId) {
        this.admId = admId;
    }

    public int getTempletId() {
        return templetId;
    }

    public void setTempletId(int templetId) {
        this.templetId = templetId;
    }

    public Date getaTime() {
        return aTime;
    }

    public void setaTime(Date aTime) {
        this.aTime = aTime;
    }

    public String getTempletTitle() {
        return templetTitle;
    }

    public void setTempletTitle(String templetTitle) {
        this.templetTitle = templetTitle;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }


    public GradeTemplet getTemplet() {
        return templet;
    }

    public void setTemplet(GradeTemplet templet) {
        this.templet = templet;
    }
}
