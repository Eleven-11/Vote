package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GradeRecordMapper {
    List<GradeRecord> findAll(@Param("start")int start,@Param("end")int end);
    int countGetAll();
    int delete(int recordId);

    int insert(GradeRecord record);
    GradeRecord findById(@Param("recordId")int recordId);

    GradeRecord findByIdRecord(@Param("recordId")int recordId);

    GradeRecord findByIdCount(@Param("recordId")int recordId);
    //统计
    List<CountSum> findByIdSta(@Param("recordId")int recordId);


    int VerificationDelete(String content);

    void update(int recordId);
    List<PhotoUser> allUser();
    int insertStatistics(GradeStatistics statistics);
    int addVote(VoteRecord record);

    List<Verification> findByIdVer(@Param("recordId")int recordId);

    List<Verification> excelList();

    //账号校验
    Verification verification(String content);
}
