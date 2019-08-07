package com.gameloft9.demo.dataaccess.model.tp;

import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class TpquestionnaireTitle implements Serializable  {
    private int titleId;
    private String title;
    private String statementVote;
    private int numberVote;
    private List<TpQuestionnaireQuestion> questionList;
    private String templateTitle;
    private List<String> sum;

    public List<String> getSum() {
        return sum;
    }

    public void setSum(List<String> sum) {
        this.sum = sum;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatementVote() {
        return statementVote;
    }

    public void setStatementVote(String statementVote) {
        this.statementVote = statementVote;
    }

    public int getNumberVote() {
        return numberVote;
    }

    public void setNumberVote(int numberVote) {
        this.numberVote = numberVote;
    }

    public List<TpQuestionnaireQuestion> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<TpQuestionnaireQuestion> questionList) {
        this.questionList = questionList;
    }
}
