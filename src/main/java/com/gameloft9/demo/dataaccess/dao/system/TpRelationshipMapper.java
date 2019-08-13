package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.TpRelationship;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TpRelationshipMapper {
    int delete (int subtitleId);
    List<TpRelationship> findBysId(int subtitleId);
}
