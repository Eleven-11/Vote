package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.TpVerification;

public interface TpVerificationService {
    boolean addVerification(TpVerification tpVerification);
    public TpVerification findbycontent(String content);
    int deleteVer(String content);
}
