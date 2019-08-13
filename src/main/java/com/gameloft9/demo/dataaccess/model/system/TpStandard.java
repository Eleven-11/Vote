package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TpStandard  implements Serializable {

   private int standardId;
   private int subtitleId;
   private String standardName;
   private int id;
   private List<TpSubstandard> zbz_coll;

}
