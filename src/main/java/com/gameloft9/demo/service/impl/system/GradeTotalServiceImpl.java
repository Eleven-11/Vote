package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.GradeTotalMapper;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.GradeTotalService;

import com.gameloft9.demo.utils.VoteContentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author 李文教
 * @date 2019/7/21 14:42
 */
@Service
@Transactional
public class GradeTotalServiceImpl implements GradeTotalService {
    @Autowired
    GradeTotalMapper gradeTotalMapper;

    @Override
    public int addTotal(GradeTotal gradeTotal) {
        GradeTemplet templet = new GradeTemplet();
        templet.setTitle(gradeTotal.getTitle());
        templet.setExplains(gradeTotal.getExplains());
        templet.setPeonum(gradeTotal.getPeonum());
        //添加 GradeTemplet的方法
        gradeTotalMapper.addTemplet(templet);




        GradeEvaluat evaluat=null;
        for (String uid:gradeTotal.getS_uid()) {
            evaluat=new GradeEvaluat();
            String [] uids=uid.split(",");
            for (String s : uids) {
                evaluat.setUid(Integer.parseInt(s));
                evaluat.setTempletId(templet.getTempletId());
                gradeTotalMapper.addEvaluat(evaluat);
            }
        }
            GradeRubric rubric=null;
            GradeAnswer answer=null;
        for (GradeRubric list : gradeTotal.getRubricList()) {
            rubric=new GradeRubric();
            rubric.setResId(list.getResId());
            rubric.setTempletId(String.valueOf(templet.getTempletId()));
            rubric.setFraction(list.getFraction());
            //添加tp_grade_rubric表的方法
            gradeTotalMapper.addRubric(rubric);
            for (GradeAnswer gradeAnswer : list.getAnswerList()) {
                answer=new GradeAnswer();
                answer.setResId(gradeAnswer.getResId());
                answer.setRubricId(rubric.getRubricId());
                answer.setPrice(gradeAnswer.getPrice());
                //添加tp_grade_answer表的方法
                gradeTotalMapper.addAnswer(answer);
            }
        }
        return templet.getTempletId();
    }
}
