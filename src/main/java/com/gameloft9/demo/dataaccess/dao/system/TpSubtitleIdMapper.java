package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.TpSubtitleId;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TpSubtitleIdMapper {
    List<TpSubtitleId> findAll();
}
