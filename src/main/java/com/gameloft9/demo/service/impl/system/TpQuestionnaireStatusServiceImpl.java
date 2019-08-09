package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpQuestionnaireStatusMapper;
import com.gameloft9.demo.dataaccess.model.tp.*;
import com.gameloft9.demo.service.api.system.TpQuestionnaireStatusService;
import com.gameloft9.demo.service.beans.system.PageRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class TpQuestionnaireStatusServiceImpl implements TpQuestionnaireStatusService {
    @Autowired
    TpQuestionnaireStatusMapper tpQuestionnaireStatusMapper;
//    public List<TpQuestionnaireStatus> findAll() {
//        return tpQuestionnaireStatusMapper.findAll();
//    }

    public List<TpQuestionnaireStatus> findAll(String page, String limit) {
        PageRange pageRange = new PageRange(page, limit);
            return tpQuestionnaireStatusMapper.findAll(pageRange.getStart(), pageRange.getEnd());
    }



    public Boolean delete(String statusId){
        tpQuestionnaireStatusMapper.delete(statusId);
        return true;
    };


    public int countGetAll(Integer statusId) {
        return tpQuestionnaireStatusMapper.countGetAll(statusId);
    }


//    public int countGetAll(String loginName, String realName, String status) {
//        return tpQuestionnaireStatusMapper.countGetAll(loginName, realName, status);
//    }
    public String update(String statusId){
        tpQuestionnaireStatusMapper.update(statusId);
        return "success";
    }

    public int add(TpquestionnaireTitle title) {

        int titleId = tpQuestionnaireStatusMapper.add(title);
        return titleId;
    }
    public int add1(TpQuestionnaireQuestion question) {
        int questionId = tpQuestionnaireStatusMapper.add1(question);
        return questionId;
    }

    public int add2(TpQuestionnaireOption option) {
        int optionId = tpQuestionnaireStatusMapper.add2(option);
        return optionId;
    }

    public int add3(TpQuestionnaireStatus status){
        int statusId = tpQuestionnaireStatusMapper.add3(status);
        return statusId;
    }

    public int add4(TpQuestionnaireTemplate template){
        int templateId = tpQuestionnaireStatusMapper.add4(template);
        return templateId;
    }

    public int findSid(int statusId) {
        return tpQuestionnaireStatusMapper.findSid(statusId);
    }

    public TpquestionnaireTitle findId(int titleId) {
        return tpQuestionnaireStatusMapper.findId(titleId);
    }

    public List<TpQuestionnaireQuestion> findId1(int questionId) {
        return tpQuestionnaireStatusMapper.findId1(questionId);
    }

    public List<TpQuestionnaireOption> findId2(int optionId) {
        return tpQuestionnaireStatusMapper.findId2(optionId);
    }

    public int addT(TpQuestionnaireStatistics statistics) {
        return tpQuestionnaireStatusMapper.addT(statistics);
    }

    public int delOid(int optionId) {
        return tpQuestionnaireStatusMapper.delOid(optionId);
    }

    public int delQid(int questionId) {
        return tpQuestionnaireStatusMapper.delQid(questionId);
    }

    public int delSid(int statusId) {
        return tpQuestionnaireStatusMapper.delSid(statusId);
    }

    public int delTid(int titleId) {
        return tpQuestionnaireStatusMapper.delTid(titleId);
    }

    public int delSaid(int statusId) {
        return tpQuestionnaireStatusMapper.delSaid(statusId);
    }

    public int findTid(int statusId) {
        return tpQuestionnaireStatusMapper.findTid(statusId);
    }

    public int delv(int statusId) {
        return tpQuestionnaireStatusMapper.delv(statusId);
    }

    public List<TpQuestionnaireOption> findOid(int questionId) {
        return tpQuestionnaireStatusMapper.findOid(questionId);
    }

    public List<TpQuestionnaireQuestion> findQid(int titleId) {
        return tpQuestionnaireStatusMapper.findQid(titleId);
    }

    public int addAccount(TpDemocraticVerification t) {
        return tpQuestionnaireStatusMapper.addAccount(t);
    }

    public TpDemocraticVerification findAccount(String content) {
        return tpQuestionnaireStatusMapper.findAccount(content);
    }

    public String updateVote(String content) {
        tpQuestionnaireStatusMapper.updateVote(content);
        return "success";
    }

    public String deleteVote(String content) {
        tpQuestionnaireStatusMapper.deleteVote(content);
        return "success";
    }

    public int Count(int optionId) {
        return tpQuestionnaireStatusMapper.Count(optionId);
    }


}
