package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

//民主测评模板表
@Data
public class TpTemplate implements Serializable {
    private int templateId;
    private int bigTitleId;
    private String templateTitleId;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date generateDate;
    private String id;

    public TpTemplate(int bigTitleId, Date generateDate, String id) {
        this.bigTitleId = bigTitleId;
        this.generateDate = generateDate;
        this.id = id;
    }
    public TpTemplate() {
    }
}
