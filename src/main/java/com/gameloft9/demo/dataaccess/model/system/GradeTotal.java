package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李文教
 * @date 2019/7/21 14:16
 */
@Data
public class GradeTotal implements Serializable {
    private String title;
    private String explains;
    private String peonum;
    private String templetTitle;

    private  List<GradeRubric> rubricList;

    private String [] s_uid;


    public String[] getS_uid() {
        return s_uid;
    }

    public void setS_uid(String[] s_uid) {
        this.s_uid = s_uid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getExplains() {
        return explains;
    }

    public void setExplains(String explains) {
        this.explains = explains;
    }

    public String getPeonum() {
        return peonum;
    }

    public void setPeonum(String peonum) {
        this.peonum = peonum;
    }

    public String getTempletTitle() {
        return templetTitle;
    }

    public void setTempletTitle(String templetTitle) {
        this.templetTitle = templetTitle;
    }

    public List<GradeRubric> getRubricList() {
        return rubricList;
    }

    public void setRubricList(List<GradeRubric> rubricList) {
        this.rubricList = rubricList;
    }

}
