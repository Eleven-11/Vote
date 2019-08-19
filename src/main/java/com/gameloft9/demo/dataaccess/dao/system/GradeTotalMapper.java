package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.*;
import org.springframework.stereotype.Repository;

@Repository
public interface GradeTotalMapper {
    void addTotal(GradeTotal gradeTotal);
    void addVerification(Verification verification);
    void addTemplet(GradeTemplet gradeTemplet);
    void addEvaluat(GradeEvaluat gradeEvaluat);
    void addRubric(GradeRubric gradeRubric);
    void addAnswer(GradeAnswer gradeAnswer);

}
