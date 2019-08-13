package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TpBigTitleMapper {
//    int insertbig(TpBigtitle big);
//    int intsersubtitle(TpSubtitleContent subtitle);
//    int intserstandard(TpStandard stanard);
//    int intsersubstandard(TpSubstandard substanard);
//
    int delete (int bigTitleId);
    TpBigTitle findbybigid(int bigTitleId);
}
