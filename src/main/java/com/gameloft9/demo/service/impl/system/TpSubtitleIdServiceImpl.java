package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpSubtitleIdMapper;
import com.gameloft9.demo.dataaccess.model.system.TpSubtitleId;
import com.gameloft9.demo.service.api.system.TpSubtitleIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Controller
@Service
public class TpSubtitleIdServiceImpl implements TpSubtitleIdService {
    @Autowired
    TpSubtitleIdMapper tpSubtitleIdMapper;
    //查询全部
    public List<TpSubtitleId> findAll() {
        return tpSubtitleIdMapper.findAll();
    }
}
