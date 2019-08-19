package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.TpDemVerification;

import java.util.List;

/**
 * @Auther: 帅气的达
 * @Date: 2019/7/20 14:17
 * @Description:
 */
public interface TpDemVerificationMapper {

    boolean addzh(TpDemVerification tpDemVerification);

    List<TpDemVerification> findzh(Integer recordId);

    boolean delzh(String content);

}
