package com.gameloft9.demo.dataaccess.model.system;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class TpBigTitle implements Serializable {

   private int bigTitleId;
   private String bigTitle;
   private String explain;
   private int number;
   private String templateTitleId;
   private List<TpSubtitleContent> list;
   private List<String> coll;
}
