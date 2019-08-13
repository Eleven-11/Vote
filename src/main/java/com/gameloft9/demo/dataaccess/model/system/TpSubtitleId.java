package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

//统计表
@Data
public class TpSubtitleId  implements  Serializable{
    private int tid;//主鍵
    private String substandardId;//子標準主鍵作爲外鍵
    private String id="56";//外鍵資源表ID
    private String uid;//外鍵人員表ID
    private String subtitleId;//子標題ID
    private String content;
}
