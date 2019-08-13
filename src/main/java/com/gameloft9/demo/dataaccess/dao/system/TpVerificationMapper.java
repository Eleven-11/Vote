package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.TpVerification;

public interface TpVerificationMapper {
    boolean addVerification(TpVerification tpVerification);
    public TpVerification findbycontent(String content);
    int deleteVer(String content);
}
