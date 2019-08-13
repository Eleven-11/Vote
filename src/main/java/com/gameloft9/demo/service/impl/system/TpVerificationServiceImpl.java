package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpVerificationMapper;
import com.gameloft9.demo.dataaccess.model.system.TpVerification;
import com.gameloft9.demo.service.api.system.TpVerificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class TpVerificationServiceImpl implements TpVerificationService {
    @Autowired
    TpVerificationMapper dao;

    public boolean addVerification(TpVerification tpVerification) {
        return dao.addVerification(tpVerification);
    }

    public TpVerification findbycontent(String content) {
        return dao.findbycontent(content);
    }

    public int deleteVer(String content) {
        return dao.deleteVer(content);
    }

}
