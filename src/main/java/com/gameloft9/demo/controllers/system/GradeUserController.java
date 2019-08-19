package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.GradeUser;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.GradeUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author 李文教
 * @date 2019/7/26 10:19
 */
@Slf4j
@Controller
@RequestMapping("/gradeUser")
public class GradeUserController {
    @Autowired
    GradeUserService gradeUserService;

    @RequestMapping(value = "/findAll.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page ,String limit){
        return new PageResultBean<Collection<GradeUser>>(gradeUserService.findAll(page,limit),gradeUserService.countGetAll());
    }

    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult delete(String username){
        return new ResultBean<Boolean>(gradeUserService.delete(username));
    }
}
