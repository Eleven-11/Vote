package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpRecordMapper;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.TpRecordService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Controller
@Service
public class TpRecordServiceImpl implements TpRecordService {
    @Autowired
    TpRecordMapper dao;

    public List<TpRecord> selectAll(String page, String limit) {
        PageRange pageRange = new PageRange(page, limit);
        return dao.selectAll(pageRange.getStart(), pageRange.getEnd());
    }

    public int countGetAll() {
        return dao.countGetAll();
    }

    public int insert(TpRecord record) {
        int recordId = dao.insert(record);
        return recordId;
    }

    public int delete(Integer recordId) {


        return  dao.delete(recordId);
    }

    public int deletesubstandardId(int substandardId) {
        return dao.deletesubstandardId(substandardId);
    }

    public int selectByrecId(Integer recordId) {
        return dao.selectByrecId(recordId);
    }

    public TpRecord getdemo(String recordId) {
        CheckUtil.notBlank(recordId,"角色id为空");
        return dao.selectById(recordId);
    }


    public int guanbi(Integer recordId) {
        dao.guanbi(recordId);
        return  0;
    }
    public int addbig(TpBigTitle big) {
        int bigTitleId = dao.insertbig(big);
        return bigTitleId;
    }

    public int addsubtitle(TpSubtitleContent subtitle) {
        int subtitleId =dao.intsersubtitle(subtitle);
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

    public int insertpralation(TpRelationship tprelationship) {

        String uid = tprelationship.getUid();
        String[] uids = uid.split(",");
        for (String uid_s : uids) {
            tprelationship.setUid(uid_s);
            int subtitleId = dao.insertpralation(tprelationship);
        }
        return 0;
    }

    public int insertsubtitletid(TpSubtitleId tpSubtitleId) {
        return dao.insertsubtitletid(tpSubtitleId);
    }

    public int findbysid(int substandardId) {
        return dao.findbysid(substandardId);
    }

    public int delver(int recordId) {
        return dao.delver(recordId);
    }
}
