package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpQuestionnaireTemplateMapper;
import com.gameloft9.demo.dataaccess.model.tp.*;
import com.gameloft9.demo.service.api.system.TpQuestionnaireTemplateService;
import com.gameloft9.demo.service.beans.system.PageRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class TpQuestionnaireTemplateServiceImpl implements TpQuestionnaireTemplateService {
    @Autowired
    TpQuestionnaireTemplateMapper tpQuestionnaireTemplateMapper;
    public List<TpQuestionnaireTemplate> findAll(String page, String limit) {
        PageRange pageRange = new PageRange(page, limit);
        return tpQuestionnaireTemplateMapper.findAll(pageRange.getStart(), pageRange.getEnd());
    }

    public int countGetAll(Integer templateId) {
        return tpQuestionnaireTemplateMapper.countGetAll(templateId);
    }

    public int add4(TpQuestionnaireTemplate template) {
        int templateId = tpQuestionnaireTemplateMapper.add4(template);
        return templateId;
    }
    public int add(TpquestionnaireTitle title) {

        int titleId = tpQuestionnaireTemplateMapper.add(title);
        return titleId;
    }
    public int add1(TpQuestionnaireQuestion question) {
        int questionId = tpQuestionnaireTemplateMapper.add1(question);
        return questionId;
    }

    public int add2(TpQuestionnaireOption option) {
        int optionId = tpQuestionnaireTemplateMapper.add2(option);
        return optionId;
    }

    public int add3(TpQuestionnaireStatus status) {
        int statusId = tpQuestionnaireTemplateMapper.add3(status);
        return statusId;
    }

    public String update(Integer templateId) {
        tpQuestionnaireTemplateMapper.update(templateId);
        return "success";
    }

    public String update1(Integer templateId) {
        tpQuestionnaireTemplateMapper.update1(templateId);
        return "success";
    }

    public int delete1(int optionId) {
        return tpQuestionnaireTemplateMapper.delete1(optionId);
    }

    public int delete2(int questionId) {
        return tpQuestionnaireTemplateMapper.delete2(questionId);
    }

    public int delete3(int templateId) {
        return tpQuestionnaireTemplateMapper.delete3(templateId);
    }

    public int delete4(int titleId) {
        return tpQuestionnaireTemplateMapper.delete4(titleId);
    }

    public int findtemplateId(int templateId) {

        return tpQuestionnaireTemplateMapper.findtemplateId(templateId);
    }

    public List<TpQuestionnaireOption> findoptionId(int questionId) {

        return  tpQuestionnaireTemplateMapper.findoptionId(questionId);
    }

    public List<TpQuestionnaireQuestion> findquestionId(int titleId) {
        return tpQuestionnaireTemplateMapper.findquestionId(titleId);
    }

    public String delete1(Integer optionId) {
        tpQuestionnaireTemplateMapper.delete1(optionId);
        return "success";
    }

    public String delete2(Integer questionId) {
        tpQuestionnaireTemplateMapper.delete2(questionId);
        return "success";
    }

    public String delete3(Integer templateId) {
        tpQuestionnaireTemplateMapper.delete3(templateId);
        return "success";
    }

    public String delete4(Integer titleId) {
        tpQuestionnaireTemplateMapper.delete4(titleId);
        return "success";
    }

    public int findTid(int templateId) {
        return tpQuestionnaireTemplateMapper.findTid(templateId);
    }

    public TpquestionnaireTitle findId(int titleId) {

        return tpQuestionnaireTemplateMapper.findId(titleId);
    }

    public List<TpQuestionnaireQuestion> findId1(int questionId) {

        return tpQuestionnaireTemplateMapper.findId1(questionId);
    }

    public List<TpQuestionnaireOption> findId2(int optionId) {

        return tpQuestionnaireTemplateMapper.findId2(optionId);
    }

}
