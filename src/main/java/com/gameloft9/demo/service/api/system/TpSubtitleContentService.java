package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.TpSubtitleContent;

import java.util.List;

public interface TpSubtitleContentService {
    List<TpSubtitleContent> findBybigTitleId(int bigTitleId);
    List<TpSubtitleContent> findBybigTitleId1(int bigTitleId);
    int delete(int bigTitleId);
}
