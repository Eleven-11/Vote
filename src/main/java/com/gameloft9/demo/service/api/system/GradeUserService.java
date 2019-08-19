package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.GradeUser;

import java.util.List;

public interface GradeUserService {
    public List<GradeUser> findAll(String page,String limit);
    int countGetAll();
    Boolean delete(String username);
}
