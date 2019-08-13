package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpStandardMapper;
import com.gameloft9.demo.dataaccess.model.system.TpStandard;
import com.gameloft9.demo.service.api.system.TpStandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
@Controller
@Service
public class TpStandardServiceImpl implements TpStandardService {
    @Autowired
    TpStandardMapper dao;
    public List<TpStandard> findBysubtitleId(int subtitleId) {
        return dao.findBysubtitleId(subtitleId);
    }

    public List<TpStandard> findBysubtitleId1(int subtitleId) {
        return dao.findBysubtitleId1(subtitleId);
    }

    public int delete(int subtitleId) {
        return dao.delete(subtitleId);
    }
}
