package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.tp.*;

import java.util.List;

public interface TpQuestionnaireTemplateService {
    public List<TpQuestionnaireTemplate> findAll(String page, String limit);

    int countGetAll(Integer templateId);

    //添加模板
    int add4(TpQuestionnaireTemplate template);
    int add(TpquestionnaireTitle title);
    int add1(TpQuestionnaireQuestion question);
    int add2(TpQuestionnaireOption option);
    int add3(TpQuestionnaireStatus status);
    //是否置顶
    String update(Integer templateId);
    String update1(Integer templateId);
    //删除
    int delete1(int optionId);
    int delete2(int questionId);
    int delete3(int templateId);
    int delete4(int titleId);
    int findtemplateId(int templateId);
    List<TpQuestionnaireOption> findoptionId(int questionId);
    List<TpQuestionnaireQuestion> findquestionId(int titleId);



    //修改
    int findTid(int templateId);
    TpquestionnaireTitle findId(int titleId);
    List<TpQuestionnaireQuestion> findId1(int questionId);
    List<TpQuestionnaireOption> findId2(int optionId);
}
