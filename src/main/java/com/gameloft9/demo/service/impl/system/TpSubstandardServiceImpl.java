package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpSubstandardMapper;
import com.gameloft9.demo.dataaccess.model.system.TpSubstandard;
import com.gameloft9.demo.service.api.system.TpSubstandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Controller
@Service
public class TpSubstandardServiceImpl implements TpSubstandardService {
    @Autowired
    TpSubstandardMapper dao;
    public List<TpSubstandard> findBystandardId(int standardId) {
        return dao.findBystandardId(standardId);
    }

    public List<TpSubstandard> findBystandardId1(int standardId) {
        return dao.findBystandardId1(standardId);
    }

    public int delete(int standardId) {
        return dao.delete(standardId);
    }
}
