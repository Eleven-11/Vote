package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.TpSubtitleContent;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TpSubtitleContentMapper {
    List<TpSubtitleContent> findBybigTitleId(int bigTitleId);
    List<TpSubtitleContent> findBybigTitleId1(int bigTitleId);
    int delete(int bigTitleId);
}
