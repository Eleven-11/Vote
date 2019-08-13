package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TpRecordMapper {
    List<TpRecord> selectAll(
            @Param("start") int start,
            @Param("end") int end);


    /**
     * 获取所有角色个数
     * */
    int countGetAll();
    int insert(TpRecord record);
    int delete(Integer recordId);
    int deletesubstandardId(int substandardId);
    int selectByrecId(Integer recordId);
    int getdemo(Integer recordId);
    TpRecord selectById(String recordId);
    int update(TpRecord rec);
    int guanbi(Integer recordId);
    int insertbig(TpBigTitle big);
    int intsersubtitle(TpSubtitleContent subtitle);
    int intserstandard(TpStandard stanard);
    int intsersubstandard(TpSubstandard substanard);
    int insertpralation(TpRelationship tprelationship);
    int insertsubtitletid(TpSubtitleId tpSubtitleId);
    int findbysid(int substandardId);
    int delver(int recordId);
}
