package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TpTemplateMapper {
    List<TpTemplate> alltemp(
            @Param("start") int start,
            @Param("end") int end);


    /**
     * 获取所有个数
     * */
    int counttemp();
    //删除
    int delete(int templateId);
    //查看
    int selectById(int templateId);
    int insertbig(TpBigTitle big);
    int intsersubtitle(TpSubtitleContent subtitle);
    int intserstandard(TpStandard stanard);
    int intsersubstandard(TpSubstandard substanard);
    int inserttem(TpTemplate template);
    //查看人员
    int insertpralation(TpRelationship tprelationship);
    //是否置顶
    int qxzd(String templateTitleId);
    int zd(String templateTitleId);
}
