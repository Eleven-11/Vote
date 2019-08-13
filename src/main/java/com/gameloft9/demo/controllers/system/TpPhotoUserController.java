package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.TpPhotoUser;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.TpPhotoUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

@Controller
@Slf4j
@RequestMapping("/photouser")
public class TpPhotoUserController {
    @Autowired(required = false)
    TpPhotoUserService tpPhotoUserServiceImpl;
    //查询全部
    @RequestMapping(value = "/list.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(){
        return new ResultBean<Collection<TpPhotoUser>>(tpPhotoUserServiceImpl.findAllUser());
    }
}
