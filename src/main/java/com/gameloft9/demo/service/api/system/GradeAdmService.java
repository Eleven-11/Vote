package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.GradeAdm;
import com.gameloft9.demo.dataaccess.model.system.GradeTemplet;
import com.gameloft9.demo.dataaccess.model.system.GradeTotal;

import java.util.List;

/**
 * @author 李文教
 * @date 2019/7/23 11:31
 */

public interface GradeAdmService {

    List<GradeAdm> findAll(String page,String limit);

    int countGetAll();

    Boolean delete(int admId);

    GradeAdm findById(Integer admId);
    GradeAdm findById2(Integer admId);


    int isTop(int admId);

    int noTop(int admId);

    int insertAdm(GradeAdm adm);

    int addTotalAdm(GradeTotal gradeTotal);
}
