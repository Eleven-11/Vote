package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.*;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.TpGradeRecordService;
import com.gameloft9.demo.service.api.system.TpGradeTempletService;
import com.gameloft9.demo.service.beans.system.PageRange;
import com.gameloft9.demo.service.beans.system.TpGradeMuBan;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Auther: 帅气的达
 * @Date: 2019/7/17 15:12
 * @Description:
 */
@Slf4j
@Service
public class TpGradeTempletServiceImpl implements TpGradeTempletService {

    @Autowired
    TpGradeTempletMapper tpGradeTempletMapper;

    TpGradeTemplet tpGradeTemplet;

    @Autowired
    TpGradeRecordService tpGradeRecordService;

    @Autowired
    TpGradeAdmMapper tp_grade_admMapper;

    @Autowired
    TpDemVerificationMapper tpDemVerificationMapper;

    Integer renshu;

    TpGraderecord tpGraderecord;

    @Autowired
    TpGradeEvaluatMapper tpGradeEvaluatMapper;

    @Autowired
    TpGradeAnswerMapper tpGradeAnswerMapper;

    @Autowired
    TpGradeRubricMapper tpGradeRubricMapper;

    @Autowired
    TpGradeRecordMapper tpGradeRecordMapper;

    public List<TpGradeUser> findAll(String page, String limit, Integer templet_Id) {

        PageRange pageRange = new PageRange(page, limit);
        return tpGradeTempletMapper.findAll(pageRange.getStart(), pageRange.getEnd(), templet_Id);
    }

    public int countGetAll(Integer username) {
        return tpGradeTempletMapper.countGetAll(username);
    }

    public boolean add(String title, String explains, Integer peonum) {
        renshu = peonum;
        tpGradeTemplet = new TpGradeTemplet(title, explains, peonum);
        return tpGradeTempletMapper.add(tpGradeTemplet);
    }

    public Integer addrub(String []tmarr, String []daarr, String []tm) {
        int z = 0;
        for (int i = 0; i < tm.length; i++){
            TpGradeRubric tpGradeRubric = new TpGradeRubric(Integer.parseInt(tm[i]), tmarr[i], tpGradeTemplet.getTemplet_Id());
            tpGradeTempletMapper.addrub(tpGradeRubric);

            for (int j = 0; j < 4; j++) {
                TpGradeAnswer tpGradeAnswer = new TpGradeAnswer(42+j, tpGradeRubric.getRubric_Id(), daarr[z]);
                tpGradeTempletMapper.addans(tpGradeAnswer);
                z++;
            }
        }

        Date date = new Date();
        String templet_Id = String.valueOf(tpGradeTemplet.getTemplet_Id());
        tpGraderecord = new TpGraderecord(templet_Id, date, "35");
        tpGradeRecordService.addjilu(tpGraderecord);

        for (int a = 0; a < renshu; a++) {
            Random r = new Random();
            StringBuffer sb = new StringBuffer(10);
            for (int j = 1; j <= 10; j++) {
                int i = r.nextInt(10);
                if (j == 1 || (j >= 8 && j <= 10)) {
                    while (i == 0) {
                        i = r.nextInt(10);
                    }
                }
                sb.append(i);
            }
            TpDemVerification tpDemVerification = new TpDemVerification(sb.toString(), tpGraderecord.getRecord_Id(),"1",49);
            tpDemVerificationMapper.addzh(tpDemVerification);
        }
        return tpGraderecord.getRecord_Id();
    }

    public boolean addmub(String[] tmarr, String[] daarr, String[] tm) {
        int z = 0;
        for (int i = 0; i < tm.length; i++){
            TpGradeRubric tpGradeRubric = new TpGradeRubric(Integer.parseInt(tm[i]), tmarr[i], tpGradeTemplet.getTemplet_Id());
            tpGradeTempletMapper.addrub(tpGradeRubric);

            for (int j = 0; j < 4; j++) {
                TpGradeAnswer tpGradeAnswer = new TpGradeAnswer(42+j, tpGradeRubric.getRubric_Id(), daarr[z]);
                tpGradeTempletMapper.addans(tpGradeAnswer);
                z++;
            }
        }
        return true;
    }


    public boolean addeva(String []uids) {
        for (int i = 0; i < uids.length; i++) {
            TpGradeEvaluat tpGradeEvaluat = new TpGradeEvaluat(Integer.parseInt(uids[i]), tpGradeTemplet.getTemplet_Id());
            tpGradeTempletMapper.addeva(tpGradeEvaluat);
        }
        return true;
    }

    public boolean addmuban(String templet_title) {
        Date date = new Date();
        TpGradeAdm tp_grade_adm = new TpGradeAdm(tpGradeTemplet.getTemplet_Id(), date, templet_title, "52");
        System.out.println(tp_grade_adm);
        tp_grade_adm.getAdmId();
        return tp_grade_admMapper.addmuban(tp_grade_adm);
    }

    public TpGradeTemplet findByTid(Integer templet_Id) {
        return tpGradeTempletMapper.findByTid(templet_Id);
    }

    public List<TpDemVerification> findzh() {
        List<TpDemVerification> findzh = new ArrayList<TpDemVerification>();

        if (tpGraderecord.getRecord_Id()!=0){
            findzh = tpDemVerificationMapper.findzh(tpGraderecord.getRecord_Id());
        }

        return findzh;
    }

    public List<TpDemVerification> getzh(Integer record_Id) {

        List<TpDemVerification> findzh = tpDemVerificationMapper.findzh(record_Id);
        return findzh;
    }

    public boolean bcmuban(TpGradeMuBan tpGradeMuBan) {

        tpGradeEvaluatMapper.delete(tpGradeMuBan.getTemplet_Id());
        List<Integer> rubId = tpGradeRubricMapper.findRubId(tpGradeMuBan.getTemplet_Id());
        for (int i = 0; i < rubId.size(); i++) {
            tpGradeAnswerMapper.delete(rubId.get(i));
        }
        tpGradeRubricMapper.delete(tpGradeMuBan.getTemplet_Id());
        tpGradeRecordMapper.delete(tpGradeMuBan.getTemplet_Id());
        tp_grade_admMapper.deletebytid(tpGradeMuBan.getTemplet_Id());
        tpGradeTempletMapper.del(tpGradeMuBan.getTemplet_Id());
        return true;
    }

    public boolean delzh(String content) {
        return tpDemVerificationMapper.delzh(content);
    }


}
