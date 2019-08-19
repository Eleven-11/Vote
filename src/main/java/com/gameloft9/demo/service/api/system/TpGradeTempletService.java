package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.service.beans.system.TpGradeMuBan;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: 帅气的达
 * @Date: 2019/7/17 15:11
 * @Description:
 */
public interface TpGradeTempletService {
    List<TpGradeUser> findAll(String page, String limit, @Param("templet_Id") Integer templet_Id);

    int countGetAll(@Param("templet_Id") Integer username);

    boolean add(String title, String explains, Integer peonum);

    Integer addrub(String []tmarr, String []daarr, String []tm);

    boolean addmub(String []tmarr, String []daarr, String []tm);

    boolean addeva(String []uids);

    boolean addmuban(String templet_title);

    TpGradeTemplet findByTid(Integer templet_Id);

    List<TpDemVerification> findzh();

    List<TpDemVerification> getzh(Integer record_Id);

    boolean bcmuban(TpGradeMuBan tpGradeMuBan);

    boolean delzh(String content);
}
