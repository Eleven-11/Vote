package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.GradeUserMapper;
import com.gameloft9.demo.dataaccess.model.system.GradeUser;
import com.gameloft9.demo.service.api.system.GradeUserService;
import com.gameloft9.demo.service.beans.system.PageRange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 李文教
 * @date 2019/7/26 10:17
 */
@Service
@Transactional
public class GradeUserServiceImpl implements GradeUserService {
    @Autowired
    GradeUserMapper gradeUserMapper;

    @Override
    public List<GradeUser> findAll(String page, String limit) {
        PageRange pageRange=new PageRange(page,limit);
        return gradeUserMapper.findAll(pageRange.getStart(),pageRange.getEnd());
    }

    @Override
    public int countGetAll() {
        return gradeUserMapper.countGetAll();
    }

    @Override
    public Boolean delete(String username) {
        gradeUserMapper.delete(username);
        return true;
    }
}
