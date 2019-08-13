package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpBigTitleMapper;
import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.api.system.TpBigTitleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@Transactional
public class TpBigTitleServiceImpl implements TpBigTitleService {
    @Autowired
    TpBigTitleMapper dao;



    public int delete(int bigTitleId) {

        return dao.delete(bigTitleId);
    }

    public TpBigTitle findbybigid(int bigTitleId) {
        return dao.findbybigid(bigTitleId);
    }

}
