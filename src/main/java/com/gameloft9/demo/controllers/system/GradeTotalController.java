package com.gameloft9.demo.controllers.system;


import com.gameloft9.demo.dataaccess.dao.system.GradeTotalMapper;
import com.gameloft9.demo.dataaccess.model.system.*;

import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;

import com.gameloft9.demo.service.api.system.GradeRecordService;
import com.gameloft9.demo.service.api.system.GradeTotalService;
import com.gameloft9.demo.utils.VoteContentUtil;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author 李文教
 * @date 2019/7/18 10:32
 */
@Slf4j
@Controller
@RequestMapping("/templ")
public class GradeTotalController {
    @Autowired
    GradeTotalService gradeTotalService;
    @Autowired
    GradeRecordService gradeRecordService;
    @Autowired
    GradeTotalMapper gradeTotalMapper;


    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult addTotal(@RequestBody GradeTotal gradeTotal){
        //System.out.println("ss");
        int temId = gradeTotalService.addTotal(gradeTotal);
        GradeRecord record=new GradeRecord();
        record.setrTime(new Date());
        record.setTempletId(temId);
        record.setResId(35);
        gradeRecordService.insert(record);

        Verification verification=null;

        for (int i=0;i<Integer.parseInt(gradeTotal.getPeonum());i++){
            verification=new Verification();
            String isVote= VoteContentUtil.generateShortUuid();
            verification.setContent(isVote);
            verification.setId(49);
            verification.setIsVote(1);
            verification.setRecordId(record.getRecordId());
            gradeTotalMapper.addVerification(verification);
        }
        //System.out.println("========="+record.getRecordId()+"");
        return new ResultBean<String>(record.getRecordId()+"");

    }
}
