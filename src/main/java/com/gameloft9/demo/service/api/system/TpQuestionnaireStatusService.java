package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.tp.*;

import java.util.List;

public interface TpQuestionnaireStatusService {
    public List<TpQuestionnaireStatus> findAll(String page, String limit);

    int countGetAll(Integer statusId);
    public Boolean delete(String statusId);

//    int countGetAll(String loginName, String realName, String status);
    String update(String statusId);

    int add(TpquestionnaireTitle title);
    int add1(TpQuestionnaireQuestion question);
    int add2(TpQuestionnaireOption option);
    int add3(TpQuestionnaireStatus status);
    int add4(TpQuestionnaireTemplate template);



    //查看
    int findSid(int statusId);
    TpquestionnaireTitle findId(int titleId);
    List<TpQuestionnaireQuestion> findId1(int questionId);
    List<TpQuestionnaireOption> findId2(int optionId);


    //提交

    int addT(TpQuestionnaireStatistics statistics);


    //删除
    int delOid(int optionId);
    int delQid(int questionId);
    int delSid(int statusId);
    int delTid(int titleId);
    int delSaid(int statusId);
    int findTid(int statusId);
    int delv(int statusId);
    List<TpQuestionnaireOption> findOid(int questionId);
    List<TpQuestionnaireQuestion> findQid(int titleId);



    //存入账号
    int addAccount(TpDemocraticVerification t);

    //校验
    TpDemocraticVerification findAccount(String content);

    //提交完变成已投票
    String updateVote(String content);

    String deleteVote(String content);

    //统计
    int Count(int optionId);



    //查看账号
    List<String> CheckAccount(int statusId);
}
