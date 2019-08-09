package com.gameloft9.demo.dataaccess.model.tp;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class TpQuestionnaireQuestion implements Serializable{
    private int questionId;
    private String question;
    private int titleId;
    private int optionRule;
    private  List<TpQuestionnaireOption> optionList;

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public int getOptionRule() {
        return optionRule;
    }

    public void setOptionRule(int optionRule) {
        this.optionRule = optionRule;
    }

    public List<TpQuestionnaireOption> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<TpQuestionnaireOption> optionList) {
        this.optionList = optionList;
    }
}
