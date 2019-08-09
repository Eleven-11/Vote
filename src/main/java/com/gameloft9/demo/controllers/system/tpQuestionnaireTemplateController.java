package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.tp.*;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.TpQuestionnaireStatusService;
import com.gameloft9.demo.service.api.system.TpQuestionnaireTemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/Template")
@Transactional
public class tpQuestionnaireTemplateController {
    @Autowired
    TpQuestionnaireTemplateService tpQuestionnaireTemplateService;
    @RequestMapping(value = "/template.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page, String limit,Integer templateId){
        return new PageResultBean<Collection<TpQuestionnaireTemplate>>(tpQuestionnaireTemplateService.findAll(page,limit),tpQuestionnaireTemplateService.countGetAll(templateId));
    }

    //创建模板
    @RequestMapping(value = "/add2.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "创建模板")
    public IResult add1(@RequestBody TpquestionnaireTitle title){
        tpQuestionnaireTemplateService.add(title);
        for(TpQuestionnaireQuestion question:title.getQuestionList()){
            question.setTitleId(title.getTitleId());
            tpQuestionnaireTemplateService.add1(question);
            for (TpQuestionnaireOption option:question.getOptionList()){
                option.setQuestionId(question.getQuestionId());
                tpQuestionnaireTemplateService.add2(option);
            }
        }
        Date date = new Date();
        TpQuestionnaireTemplate template = new TpQuestionnaireTemplate(title.getTitleId(),date,"53");
        template.setTemplateTitle(title.getTemplateTitle());
        tpQuestionnaireTemplateService.add4(template);
        return new ResultBean<String>("success");
    }

//取消置顶
    @RequestMapping(value = "/update1.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "取消置顶")
    public IResult update(Integer templateId){
        return new ResultBean<String>(tpQuestionnaireTemplateService.update(templateId));
    }


    //置顶
    @RequestMapping(value = "/update2.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "置顶")
    public IResult update1(Integer templateId){
        return new ResultBean<String>(tpQuestionnaireTemplateService.update1(templateId));
    }



    //使用
    @RequestMapping(value = "/getAll.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "使用")
    public IResult getAll(Integer templateId){
        int titleId = tpQuestionnaireTemplateService.findTid(templateId);
        TpquestionnaireTitle tpquestionnaireTitle = tpQuestionnaireTemplateService.findId(titleId);
        List<TpQuestionnaireQuestion> question = tpQuestionnaireTemplateService.findId1(titleId);
        for (TpQuestionnaireQuestion tpQuestionnaireQuestion : question) {
            List<TpQuestionnaireOption> optionList = tpQuestionnaireTemplateService.findId2(tpQuestionnaireQuestion.getQuestionId());
            tpQuestionnaireQuestion.setOptionList(optionList);
        }
        tpquestionnaireTitle.setQuestionList(question);
        return new ResultBean<TpquestionnaireTitle>(tpquestionnaireTitle);
    }


    //删除
    @RequestMapping(value = "/delete2.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除")
    public IResult delete(Integer templateId){
        int titleId = tpQuestionnaireTemplateService.findtemplateId(templateId);
        List<TpQuestionnaireQuestion> tpQuestionnaireQuestions = tpQuestionnaireTemplateService.findquestionId(titleId);
        for (TpQuestionnaireQuestion question : tpQuestionnaireQuestions){
            List<TpQuestionnaireOption> tpQuestionnaireOptions = tpQuestionnaireTemplateService.findoptionId(question.getQuestionId());
                tpQuestionnaireTemplateService.delete1(question.getQuestionId());
        }
        tpQuestionnaireTemplateService.delete2(titleId);
        tpQuestionnaireTemplateService.delete3(templateId);
        tpQuestionnaireTemplateService.delete4(titleId);

        return new ResultBean<Boolean>();
    }
}

