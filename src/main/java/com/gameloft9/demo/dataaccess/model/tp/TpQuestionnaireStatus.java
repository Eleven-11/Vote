package com.gameloft9.demo.dataaccess.model.tp;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TpQuestionnaireStatus {
    private String statusId;
    private String history;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date publicationDate;
    private String status;
    private TpResourcelist tpResourcelist;

    public String getStatusId() {
        return statusId;
    }

    public void setStatusId(String statusId) {
        this.statusId = statusId;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TpResourcelist getTpResourcelist() {
        return tpResourcelist;
    }

    public void setTpResourcelist(TpResourcelist tpResourcelist) {
        this.tpResourcelist = tpResourcelist;
    }

    public TpQuestionnaireStatus(String history, Date publicationDate, String status) {
        this.history = history;
        this.publicationDate = publicationDate;
        this.status = status;
    }

    public TpQuestionnaireStatus() {
    }
}
