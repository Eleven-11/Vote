package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.TpPhotoUser;

import java.util.List;

public interface TpPhotoUserService {
    List<TpPhotoUser> findAllUser();


    /**
     * 获取所有个数
     * */

    TpPhotoUser findbyuid(int uid);
}
