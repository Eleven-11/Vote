package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpRelationshipMapper;
import com.gameloft9.demo.dataaccess.model.system.TpRelationship;
import com.gameloft9.demo.service.api.system.TpRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Controller
@Service
public class TpRelationshipServiceImpl implements TpRelationshipService {
    @Autowired
    TpRelationshipMapper dao;

    public int delete(int subtitleId) {
        return dao.delete(subtitleId);
    }

    public List<TpRelationship> findBysId(int subtitleId) {
        return dao.findBysId(subtitleId);
    }
}
