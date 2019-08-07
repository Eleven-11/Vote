package com.gameloft9.demo.dataaccess.model.tp;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class TpQuestionnaireTemplate implements Serializable {
    private int templateId;
    private int titleId;
    private String templateTitle;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date generationDate;
    private String roofPlacement;

    public TpQuestionnaireTemplate(int titleId, Date generationDate, String roofPlacement) {
        this.titleId = titleId;
        this.generationDate = generationDate;
        this.roofPlacement = roofPlacement;
    }

    public TpQuestionnaireTemplate() {
    }




    public int getTemplateId() {
        return templateId;
    }

    public void setTemplateId(int templateId) {
        this.templateId = templateId;
    }

    public int getTitleId() {
        return titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public String getTemplateTitle() {
        return templateTitle;
    }

    public void setTemplateTitle(String templateTitle) {
        this.templateTitle = templateTitle;
    }

    public Date getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(Date generationDate) {
        this.generationDate = generationDate;
    }

    public String getRoofPlacement() {
        return roofPlacement;
    }

    public void setRoofPlacement(String roofPlacement) {
        this.roofPlacement = roofPlacement;
    }
}
