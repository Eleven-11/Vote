package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.tp.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TpQuestionnaireTemplateMapper {
    /**
     * 分页获取用户列表
     */
    public List<TpQuestionnaireTemplate> findAll(@Param("start") int start, @Param("end") int end);

    /**
     * 获取用户列表大小
     */
    int countGetAll(@Param("templateId") Integer templateId);
//    int countGetAll(@Param("loginName") String loginName,
//                    @Param("realName") String realName,
//                    @Param("isForbidden") String isForbidden);
    //添加模板
    int add4(TpQuestionnaireTemplate template);
    int add(TpquestionnaireTitle title);
    int add1(TpQuestionnaireQuestion question);
    int add3(TpQuestionnaireStatus status);
    int add2(TpQuestionnaireOption option);
//是否置顶
    void update(Integer templateId);
    void update1(Integer templateId);

//删除
    int delete1(int optionId);
    int delete2(int questionId);
    int delete3(int templateId);
    int delete4(int titleId);
    int findtemplateId(int templateId);
    List<TpQuestionnaireOption> findoptionId(int questionId);
    List<TpQuestionnaireQuestion> findquestionId(int titleId);


    //使用
    int findTid(int templateId);
    TpquestionnaireTitle findId(int titleId);
    List<TpQuestionnaireQuestion> findId1(int questionId);
    List<TpQuestionnaireOption> findId2(int optionId);


}
