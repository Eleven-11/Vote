package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpTemplateMapper;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.TpTemplateService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Controller
@Service
public class TpTemplateServiceImpl implements TpTemplateService {
    @Autowired
    TpTemplateMapper dao;

    public List<TpTemplate> alltemp(String page, String limit) {
        PageRange pageRange = new PageRange(page, limit);
        return dao.alltemp(pageRange.getStart(), pageRange.getEnd());
    }
    //删除
    public int delete(int templateId) {
        return dao.delete(templateId);
    }
    //查看
    public int selectById(int templateId) {
        return dao.selectById(templateId);
    }

    public int counttemp() {
        return dao.counttemp();
    }

    public int addbig(TpBigTitle big) {
        int bigTitleId = dao.insertbig(big);
        return bigTitleId;
    }

    public int addsubtitle(TpSubtitleContent subtitle) {
        int subtitleId = dao.intsersubtitle(subtitle);
        return subtitleId;
    }

    public int addstandard(TpStandard standard) {
        int standardId = dao.intserstandard(standard);
        return standardId;
    }

    public int addsubstandard(TpSubstandard substandard) {
        int substandardId = dao.intsersubstandard(substandard);
        return substandardId;
    }

    public int inserttem(TpTemplate template) {
        int templateId = dao.inserttem(template);
        return templateId;
    }

    public int qxzd(String templateTitleId) {
        dao.qxzd(templateTitleId);
        return 0;
    }

    public int zd(String templateTitleId) {
        dao.zd(templateTitleId);
        return 0;
    }
    public int insertpralation(TpRelationship tprelationship) {

        String uid = tprelationship.getUid();
        String[] uids = uid.split(",");
        for (String uid_s : uids) {
            tprelationship.setUid(uid_s);
            int subtitleId = dao.insertpralation(tprelationship);
        }
        return 0;
    }

}
