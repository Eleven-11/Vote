package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.GradeRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.IntrospectionException;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Controller
@RequestMapping("/record2")
public class GradeRecordController {
    @Autowired
    GradeRecordService gradeRecordServiceImpl;

    @RequestMapping(value ="/findAll.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page,String limit){
        List<GradeRecord> gradeList = gradeRecordServiceImpl.findAll(page,limit);
        return new PageResultBean<Collection<GradeRecord>>(gradeList,gradeRecordServiceImpl.countGetAll());
    }


    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除")
    public IResult delete( int recordId){
        gradeRecordServiceImpl.delete(recordId);
        return new ResultBean<Boolean>(true);
    }


    @RequestMapping(value = "/findById.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findById(Integer recordId){
        return new ResultBean<GradeRecord>(gradeRecordServiceImpl.findById(recordId));
    }

    @RequestMapping(value = "/findByIdRecord.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findByIdRecord(Integer recordId){
        return new ResultBean<GradeRecord>(gradeRecordServiceImpl.findByIdRecord(recordId));
    }

    @RequestMapping(value = "/findByIdCount.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findByIdCount(Integer recordId){
        return new ResultBean<GradeRecord>(gradeRecordServiceImpl.findByIdCount(recordId));
    }

    /**
     *   校验
     */
    @RequestMapping(value = "/verification.do", method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD, memo = "校验")
    public IResult verification(@RequestBody Verification verification) {
        String content = verification.getContent();
        Verification ver = gradeRecordServiceImpl.verification(content);
        if(ver==null){
            return new ResultBean<Boolean>(false);
        }else {
            int recordId = verification.getRecordId();
            int recordId1 = ver.getRecordId();
            if (recordId == recordId1) {
                return new ResultBean<Boolean>(true);

            } else {
                return new ResultBean<Boolean>(false);
            }
        }
    }

    @RequestMapping(value = "/findByIdVer.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findByIdVer(Integer recordId){
        return new ResultBean<Collection<Verification>>(gradeRecordServiceImpl.findByIdVer(recordId));
    }
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "关闭投票")
    public IResult update(int recordId){
        return new ResultBean<Integer>(gradeRecordServiceImpl.update(recordId));
    }

    @RequestMapping(value ="/findAllUname.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAllUname(){
        List<PhotoUser> data = gradeRecordServiceImpl.allUser();
        return new ResultBean<Collection<PhotoUser>>(data);
    }
    //查看的提交
    @RequestMapping(value = "/insertStatistics.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(@RequestBody VoteRecord record){
        gradeRecordServiceImpl.addVote(record);

        gradeRecordServiceImpl.VerificationDelete(record.getContent());
        return new ResultBean<String>("success");
    }
    @RequestMapping(value = "/exportExcel",method = RequestMethod.POST)
    @ResponseBody
    public void export(String recordId,HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IntrospectionException, IllegalAccessException, ParseException, InvocationTargetException {
        System.out.println("excel导出开始");
        System.out.println("list:"+recordId);
        if(recordId!=""){
            response.reset(); //清除buffer缓存
            Map<String,Object> map=new HashMap<String,Object>();
            System.out.println("文件名：");
            // 指定下载的文件名
            response.setHeader("Content-Disposition", "attachment;filename="+recordId+".xlsx");
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            HSSFWorkbook workbook=null;
            //导出Excel对象
            try {
                workbook = gradeRecordServiceImpl.exportExcelInfo(recordId);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IntrospectionException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            OutputStream output;
            try {
                output = response.getOutputStream();
                BufferedOutputStream bufferedOutPut = new BufferedOutputStream(output);
                bufferedOutPut.flush();
                workbook.write(bufferedOutPut);
                bufferedOutPut.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
