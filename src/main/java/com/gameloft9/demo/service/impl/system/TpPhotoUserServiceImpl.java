package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.TpPhotoUserMapper;
import com.gameloft9.demo.dataaccess.model.system.TpPhotoUser;
import com.gameloft9.demo.service.api.system.TpPhotoUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
@Controller
@Service
public class TpPhotoUserServiceImpl implements TpPhotoUserService {
    @Autowired
    TpPhotoUserMapper dao;


    public List<TpPhotoUser> findAllUser() {

        return dao.findAllUser();
    }

    public TpPhotoUser findbyuid(int uid) {

        return dao.findbyuid(uid);
    }
}
