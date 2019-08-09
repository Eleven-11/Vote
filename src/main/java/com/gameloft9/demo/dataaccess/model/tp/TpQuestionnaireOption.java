package com.gameloft9.demo.dataaccess.model.tp;

import lombok.Data;

import java.io.Serializable;

@Data
public class TpQuestionnaireOption  implements Serializable{
    private int optionId;
    private String option;
    private int questionId;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public TpQuestionnaireOption() {
    }

    public TpQuestionnaireOption(int optionId) {
        this.optionId = optionId;
    }
    public TpQuestionnaireOption(String tpQuestionnaireOption) { }





    public int getOptionId() {
        return optionId;
    }

    public void setOptionId(int optionId) {
        this.optionId = optionId;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
}
