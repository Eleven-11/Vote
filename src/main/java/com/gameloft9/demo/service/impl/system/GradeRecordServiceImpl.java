package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.GradeRecordMapper;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.GradeRecordService;
import com.gameloft9.demo.service.beans.system.ExcelBean;
import com.gameloft9.demo.service.beans.system.ExcelClass;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.utils.ExcelUtil;
import com.gameloft9.demo.utils.ExportDataToExcle;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.formula.functions.Count;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

@Service
@Transactional
 public class GradeRecordServiceImpl implements GradeRecordService {
    @Autowired
    GradeRecordMapper gradeRecordMapper;


    @Override
    public List<GradeRecord> findAll(String page, String limit) {
        PageRange pageRange = new PageRange(page, limit);
        return gradeRecordMapper.findAll(pageRange.getStart(), pageRange.getEnd());
    }

    @Override
    public int countGetAll() {
        return gradeRecordMapper.countGetAll();
    }

    @Override
    public Boolean delete(int recordId) {
        gradeRecordMapper.delete(recordId);
        return true;
    }

    @Override
    public Boolean VerificationDelete(String content) {
        gradeRecordMapper.VerificationDelete(content);
        return true;
    }


    @Override
    public int insert(GradeRecord record) {
        return gradeRecordMapper.insert(record);
    }

    @Override
    public GradeRecord findById(Integer recordId) {
        return gradeRecordMapper.findById(recordId);
    }

    @Override
    public GradeRecord findByIdRecord(Integer recordId) {
        return gradeRecordMapper.findByIdRecord(recordId);
    }

    @Override
    public GradeRecord findByIdCount(Integer recordId) {
        GradeRecord gradeRecord = gradeRecordMapper.findByIdCount(recordId);
        List<CountSum> list = gradeRecordMapper.findByIdSta(recordId);
        gradeRecord.setCountSums(list);
        return gradeRecord;
    }

    @Override
    public int update(int recordId) {
        gradeRecordMapper.update(recordId);
        return 1;
    }

    @Override
    public List<PhotoUser> allUser() {
        return gradeRecordMapper.allUser();
    }

    @Override
    public void addVote(VoteRecord record) {
        GradeStatistics statistics = new GradeStatistics();
        statistics.setRecordId(Integer.parseInt(record.getRecordId()));
        for (Dog dogs : record.getDogList()) {
            statistics.setUid(Integer.parseInt(dogs.getUid()));
            for (Cat cats : dogs.getCatList()) {
                statistics.setAnswerId(Integer.parseInt(cats.getOid()));
                gradeRecordMapper.insertStatistics(statistics);
            }
        }
    }

    @Override
    public List<Verification> findByIdVer(Integer recordId) {
        return gradeRecordMapper.findByIdVer(recordId);
    }

    @Override
    public Verification verification(String content) {
        return gradeRecordMapper.verification(content);
    }


    @Override
    public HSSFWorkbook exportExcelInfo(String salaryDate) throws InvocationTargetException, ClassNotFoundException, IntrospectionException, ParseException, IllegalAccessException {
        List<Verification> list =gradeRecordMapper.findByIdVer(Integer.parseInt(salaryDate));
//        List<ExcelBean> excel=new ArrayList<>();
//        Map<Integer,List<ExcelBean>> map=new ExcelClass().contentExcel();
        HSSFWorkbook xssfWorkbook=null;
//        String sheetName = salaryDate + "分享内容";
//        //调用ExcelUtil的方法

        try {
            xssfWorkbook = ExportDataToExcle.export(list,"E:\\Temp\\账号信息.xls","com.gameloft9.demo.dataaccess.model.system.Verification");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return xssfWorkbook;
    }
}
