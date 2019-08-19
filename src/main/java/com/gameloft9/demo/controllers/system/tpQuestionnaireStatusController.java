package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.tp.*;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.TpQuestionnaireStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.transform.Result;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/WJ")
@Transactional
public class tpQuestionnaireStatusController {

    @Autowired
    TpQuestionnaireStatusService tpQuestionnaireStatusService;
    @RequestMapping(value = "/status.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page, String limit,Integer statusId){
        return new PageResultBean<Collection<TpQuestionnaireStatus>>(tpQuestionnaireStatusService.findAll(page,limit),tpQuestionnaireStatusService.countGetAll(statusId));
    }



    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "关闭投票")
    public IResult update(String statusId){
        return new ResultBean<String>(tpQuestionnaireStatusService.update(statusId));
    }

//发布
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加")
        public IResult add(@RequestBody TpquestionnaireTitle title){
        int titleId = tpQuestionnaireStatusService.add(title);

        for(TpQuestionnaireQuestion question:title.getQuestionList()){
            question.setTitleId(title.getTitleId());
            int questionId = tpQuestionnaireStatusService.add1(question);
            for(TpQuestionnaireOption option:question.getOptionList()){
                option.setQuestionId(question.getQuestionId());
                tpQuestionnaireStatusService.add2(option);
            }

        }
        Date date = new Date();
        TpQuestionnaireStatus tpQuestionnaireStatus = new TpQuestionnaireStatus(String.valueOf(title.getTitleId()),date,"35");
        tpQuestionnaireStatusService.add3(tpQuestionnaireStatus);
        List<String> sum = title.getSum();
        for (String s : sum) {
            TpDemocraticVerification tpDemocraticVerification = new TpDemocraticVerification(47,s,Integer.valueOf(tpQuestionnaireStatus.getStatusId()),1);
           tpQuestionnaireStatusService.addAccount(tpDemocraticVerification);
        }

        return new  ResultBean<String>("success");
    }
//存为模板
@RequestMapping(value = "/add1.do",method = RequestMethod.POST)
@ResponseBody
@BizOperLog(operType = OperType.ADD,memo = "存为模板")
public IResult add1(@RequestBody TpquestionnaireTitle title){
    tpQuestionnaireStatusService.add(title);
    for(TpQuestionnaireQuestion question:title.getQuestionList()){
        question.setTitleId(title.getTitleId());
        tpQuestionnaireStatusService.add1(question);
        for (TpQuestionnaireOption option:question.getOptionList()){
            option.setQuestionId(question.getQuestionId());
            tpQuestionnaireStatusService.add2(option);
        }
    }
    Date date = new Date();
    TpQuestionnaireTemplate template = new TpQuestionnaireTemplate(title.getTitleId(),date,"53");
    template.setTemplateTitle(title.getTemplateTitle());
    tpQuestionnaireStatusService.add4(template);
    return new ResultBean<String>("success");
}

    //查看
    @RequestMapping(value = "/Get.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "查看")
    public IResult getAll(Integer statusId){
        int titleId = tpQuestionnaireStatusService.findSid(statusId);
        TpquestionnaireTitle tpquestionnaireTitle = tpQuestionnaireStatusService.findId(titleId);
        List<TpQuestionnaireQuestion> question = tpQuestionnaireStatusService.findId1(titleId);
        for (TpQuestionnaireQuestion tpQuestionnaireQuestion : question) {
            List<TpQuestionnaireOption> optionList = tpQuestionnaireStatusService.findId2(tpQuestionnaireQuestion.getQuestionId());

            tpQuestionnaireQuestion.setOptionList(optionList);

        }
        tpquestionnaireTitle.setQuestionList(question);
        return new ResultBean<TpquestionnaireTitle>(tpquestionnaireTitle);
    }



    //提交
    @RequestMapping(value = "/Tijiao.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "提交")
    public IResult Tijiao(@RequestBody TpQuestionnaireStatistics statistics){
        List<String> optionIds = statistics.getOptionIds();
        String statusId = statistics.getStatusId();
        for (String option : optionIds) {
//            int optionId = option.getOptionId();
            if(option==null){
                return new ResultBean<String>("false");
            }else{
                TpQuestionnaireStatistics t = new TpQuestionnaireStatistics(String.valueOf(option),statusId);
                tpQuestionnaireStatusService.addT(t);
                tpQuestionnaireStatusService.deleteVote(statistics.getContent());
            }

        }
        //1是为投票   0是已投票
//        tpQuestionnaireStatusService.updateVote(statistics.getContent());
        return new ResultBean<String>("success");
    }

    //删除
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除用户")
    public IResult delete(Integer statusId){
        tpQuestionnaireStatusService.delv(statusId);
        tpQuestionnaireStatusService.delSaid(statusId);
        int titleId = tpQuestionnaireStatusService.findTid(statusId);
        List<TpQuestionnaireQuestion> questionId = tpQuestionnaireStatusService.findQid(titleId);
        for (TpQuestionnaireQuestion question : questionId) {
            List<TpQuestionnaireOption> optionId = tpQuestionnaireStatusService.findOid(question.getQuestionId());
            tpQuestionnaireStatusService.delOid(question.getQuestionId());
        }
        tpQuestionnaireStatusService.delQid(titleId);
        tpQuestionnaireStatusService.delSid(statusId);
        tpQuestionnaireStatusService.delTid(titleId);
        return new ResultBean<Boolean>();
    }

    //存入账号
//    @RequestMapping(value = "/Account.do",method = RequestMethod.POST)
//    @ResponseBody
//    @BizOperLog(operType = OperType.ADD,memo = "存入账号")
//    public IResult addAccount(TpDemocraticVerification t){
//        List<String> num = t.getNum();
//        for (String s : num) {
//            TpDemocraticVerification tpDemocraticVerification = new TpDemocraticVerification(47,s,35,1);
//            tpQuestionnaireStatusService.addAccount(tpDemocraticVerification);
//        }
//
//        return new ResultBean<String>("success");
//    }
    //校验
    @RequestMapping(value = "/Account.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult account(@RequestBody TpDemocraticVerification verification){
        String content = verification.getContent();
        TpDemocraticVerification account = tpQuestionnaireStatusService.findAccount(content);
        if(account==null){
            return new ResultBean<Boolean>(false);
        }else{
//            return new ResultBean<Boolean>(true);
            int recordId = verification.getRecordId();
            int recordId1 = account.getRecordId();
            if(recordId==recordId1){
                return new ResultBean<Boolean>(true);

            }else{
                return new ResultBean<Boolean>(false);
            }
        }
    }



    //统计
    @RequestMapping(value = "/Count.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult count(Integer statusId){
        int titleId = tpQuestionnaireStatusService.findSid(statusId);
        TpquestionnaireTitle tpquestionnaireTitle = tpQuestionnaireStatusService.findId(titleId);
        List<TpQuestionnaireQuestion> question = tpQuestionnaireStatusService.findId1(titleId);
        for (TpQuestionnaireQuestion tpQuestionnaireQuestion : question) {
            List<TpQuestionnaireOption> optionList = tpQuestionnaireStatusService.findId2(tpQuestionnaireQuestion.getQuestionId());
            for (TpQuestionnaireOption option : optionList) {
                int optionId = option.getOptionId();
                int count = tpQuestionnaireStatusService.Count(optionId);
                option.setCount(count);
            }
            tpQuestionnaireQuestion.setOptionList(optionList);
        }
        tpquestionnaireTitle.setQuestionList(question);
        return new ResultBean<TpquestionnaireTitle>(tpquestionnaireTitle);
    }



    //查看账号
    @RequestMapping(value = "/CheckAccount.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult CheckAccount(Integer statusId){
        List<String> strings = tpQuestionnaireStatusService.CheckAccount(statusId);
        return new ResultBean<List<String>>(strings);
    }
}
