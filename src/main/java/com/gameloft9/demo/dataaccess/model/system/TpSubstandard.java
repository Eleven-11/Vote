package com.gameloft9.demo.dataaccess.model.system;

import lombok.Data;

import java.io.Serializable;

@Data
public class TpSubstandard implements Serializable {

   private int substandardId;
   private int standardId;
   private String substandardName;
   private int sum;
}
