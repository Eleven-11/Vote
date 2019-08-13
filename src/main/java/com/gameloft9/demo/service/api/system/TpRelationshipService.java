package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.TpRelationship;

import java.util.List;

public interface TpRelationshipService {
    int delete (int subtitleId);
    List<TpRelationship> findBysId(int subtitleId);
}
