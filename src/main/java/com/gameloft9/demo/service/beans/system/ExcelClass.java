package com.gameloft9.demo.service.beans.system;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * @author 李文教
 * @date 2019/8/11 13:53
 */
public class ExcelClass {
    public  Map<Integer,List<ExcelBean>> contentExcel(){
        List<ExcelBean> excel=new ArrayList<>();
        Map<Integer,List<ExcelBean>> map=new LinkedHashMap<>();
        XSSFWorkbook xssfWorkbook=null;
        //设置标题栏
        excel.add(new ExcelBean("序号","id",0));
        excel.add(new ExcelBean("账号","content",0));
        map.put(0, excel);
        return map;
    }
}
