package com.gameloft9.demo.dataaccess.model.tp;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TpQuestionnaireStatistics implements Serializable {
    private String statisticsId;
    private String optionId;
    private String statusId;
    private List<String> optionIds;
    private String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public TpQuestionnaireStatistics(String optionId, String statusId) {
        this.optionId = optionId;
        this.statusId = statusId;
    }

    public TpQuestionnaireStatistics() {
    }

    public String getStatisticsId() {
        return statisticsId;
    }

    public void setStatisticsId(String statisticsId) {
        this.statisticsId = statisticsId;
    }

    public String getOptionId() {
        return optionId;
    }

    public void setOptionId(String optionId) {
        this.optionId = optionId;
    }

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public List<String> getOptionIds() {
        return optionIds;
    }

    public void setOptionIds(List<String> optionIds) {
        this.optionIds = optionIds;
    }
}
