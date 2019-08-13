package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.*;

import java.util.List;

public interface TpRecordService {
    /**
     * 获取所有角色
     * @param page 页序
     * @param limit 分页大小
     * */
    List<TpRecord> selectAll(String page, String limit);

    /**
     * 获取所有角色个数
     * */
    int countGetAll();
    int insert(TpRecord record);
    int delete(Integer recordId);
    int deletesubstandardId(int substandardId);
    /**
     * 获取角色
     * @param recordId 角色id
     */
    int selectByrecId(Integer recordId);

    /**
     * 更新角色

     * */
    int guanbi(Integer recordId);
    int addbig(TpBigTitle big);
    int addsubtitle(TpSubtitleContent subtitle);
    int addstandard(TpStandard standard);
    int addsubstandard(TpSubstandard substandard);
    int insertpralation(TpRelationship tprelationship);
    int insertsubtitletid(TpSubtitleId tpSubtitleId);
    int findbysid(int substandardId);
    int delver(int recordId);
}
