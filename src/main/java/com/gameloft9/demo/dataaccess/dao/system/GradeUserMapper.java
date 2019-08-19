package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.GradeUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 李文教
 * @date 2019/7/14 16:56
 */
@Repository
public interface GradeUserMapper {
    public List<GradeUser> findAll(@Param("start")int start,@Param("end")int end);
    int countGetAll();
    int delete(String username);
}
