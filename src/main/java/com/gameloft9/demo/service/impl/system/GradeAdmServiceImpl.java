package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.GradeAdmMapper;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.GradeAdmService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 李文教
 * @date 2019/7/23 11:32
 */
@Service
@Transactional
public class GradeAdmServiceImpl implements GradeAdmService {
    @Autowired
    GradeAdmMapper admMapper;

    @Override
    public List<GradeAdm> findAll(String page, String limit) {
        PageRange pageRange=new PageRange(page,limit);
        return admMapper.findAll(pageRange.getStart(),pageRange.getEnd());
    }

    @Override
    public int countGetAll() {
        return admMapper.countGetAll();
    }

    @Override
    public Boolean delete(int admId) {
        admMapper.delete(admId);
        return true;
    }

    @Override
    public GradeAdm findById(Integer admId) {
        return admMapper.findById(admId);
    }

    @Override
    public GradeAdm findById2(Integer admId) {
        return admMapper.findById2(admId);
    }


    @Override
    public int isTop(int admId) {
        return admMapper.isTop(admId);//取消置顶
    }

    @Override
    public int noTop(int admId) {
        return admMapper.noTop(admId);//置顶
    }

    @Override
    public int insertAdm(GradeAdm adm) {
        return admMapper.insertAdm(adm);
    }

    @Override
    public int addTotalAdm(GradeTotal gradeTotal) {
        GradeTemplet templet = new GradeTemplet();
        templet.setTitle(gradeTotal.getTitle());
        templet.setExplains(gradeTotal.getExplains());
        templet.setPeonum(gradeTotal.getPeonum());
        //添加 GradeTemplet的方法
        admMapper.addTempletAdm(templet);


        GradeEvaluat evaluat = null;
        for (String uid : gradeTotal.getS_uid()) {
            evaluat = new GradeEvaluat();
            String[] uids = uid.split(",");
            for (String s : uids) {
                evaluat.setUid(Integer.parseInt(s));
                evaluat.setTempletId(templet.getTempletId());
                admMapper.addEvaluatAdm(evaluat);
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
            admMapper.addRubricAdm(rubric);
            for (GradeAnswer gradeAnswer : list.getAnswerList()) {
                answer=new GradeAnswer();
                answer.setResId(gradeAnswer.getResId());
                answer.setRubricId(rubric.getRubricId());
                answer.setPrice(gradeAnswer.getPrice());
                //添加tp_grade_answer表的方法
                admMapper.addAnswerAdm(answer);
            }
        }
        return templet.getTempletId();
    }
}
