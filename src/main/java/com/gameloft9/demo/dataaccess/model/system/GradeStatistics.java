package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author 李文教
 * @date 2019/8/9 11:17
 */
@Data
public class GradeStatistics implements Serializable {
    private int statisticsId;
    private int recordId;
    private int answerId;
    private int uid;
    private List<GradeAnswer> answers;
    private List<GradeEvaluat> evaluatList;




    public List<GradeAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<GradeAnswer> answers) {
        this.answers = answers;
    }

    public int getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(int statisticsId) {
        this.statisticsId = statisticsId;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
