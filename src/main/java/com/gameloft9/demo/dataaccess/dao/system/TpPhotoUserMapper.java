package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.TpPhotoUser;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TpPhotoUserMapper {
    List<TpPhotoUser> findAllUser();
    TpPhotoUser findbyuid(int uid);
}
