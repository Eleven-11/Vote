package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.*;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

import java.util.List;


public interface GradeRecordService {
    List<GradeRecord> findAll(String page,String limit);
    int countGetAll();
    Boolean delete(int recordId);
    Boolean VerificationDelete(String content);
    int insert(GradeRecord record);
    GradeRecord findById(Integer recordId);
    GradeRecord findByIdRecord(Integer recordId);
    GradeRecord findByIdCount(Integer recordId);

    int update(int recordId);
    List<PhotoUser> allUser();
    //int insertStatistics(GradeStatistics statistics);
    void addVote(VoteRecord record);

    List<Verification> findByIdVer(Integer recordId);
    //账号校验
    Verification verification(String content);



    /**
     * 导出Excel表格
     * @param salaryDate
     * @return
     * @throws InvocationTargetException
     * @throws ClassNotFoundException
     * @throws IntrospectionException
     * @throws ParseException
     * @throws IllegalAccessException
     */
    HSSFWorkbook exportExcelInfo(String salaryDate) throws InvocationTargetException,
            ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException;

}
