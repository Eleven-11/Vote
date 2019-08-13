package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.TpStandard;
import com.gameloft9.demo.dataaccess.model.system.TpSubtitleContent;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TpStandardMapper {
    List<TpStandard> findBysubtitleId(int subtitleId);
    List<TpStandard> findBysubtitleId1(int subtitleId);
    int delete(int subtitleId);
}
