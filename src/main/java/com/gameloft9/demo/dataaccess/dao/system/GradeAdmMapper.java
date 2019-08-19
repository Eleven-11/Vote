package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeAdmMapper {
    //查询分页全部
    List<GradeAdm> findAll(@Param("start")int start, @Param("end")int end);
    int countGetAll();
    //删除
    int delete(int admId);
    //根据Id查询
    GradeAdm findById(@Param("admId") int admId);

    GradeAdm findById2(@Param("admId") int admId);

    //是置顶
    int isTop(int admId);
    //不置顶
    int noTop(int admId);
    //增加tp_grade_adm表
    int insertAdm(GradeAdm adm);
    //这是增加页面所有到后台的
    void addTotalAdm(GradeTotal gradeTotal);
    //增加tp_grade_templet表
    void addTempletAdm(GradeTemplet gradeTemplet);
    //增加tp_grade_evaluat表
    void addEvaluatAdm(GradeEvaluat gradeEvaluat);
    //增加tp_grade_rubric表
    void addRubricAdm(GradeRubric gradeRubric);
    //增加tp_grade_answer表
    void addAnswerAdm(GradeAnswer gradeAnswer);
}
