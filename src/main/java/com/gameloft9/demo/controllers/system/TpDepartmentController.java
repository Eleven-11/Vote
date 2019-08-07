package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.SysOrganizeTest;
import com.gameloft9.demo.dataaccess.model.tp.TpDepartment;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.SysDepartmentService;
import com.gameloft9.demo.service.api.system.SysOrgService;
import com.gameloft9.demo.dataaccess.model.tp.OrgNodeResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * 组织机构
 * Created by gameloft9 on 2017/12/19.
 */
@Controller
@Slf4j
@RequestMapping("/depart")
public class TpDepartmentController {

    @Autowired
    SysDepartmentService sysDepartmentService;

    /**
     * 获取所有组织机构
     * */
    @RequestMapping(value = "/All.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getAll(){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Collection<OrgNodeResponse>>(sysDepartmentService.getAll());
    }

    /**
     * 获取组织机构
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult get(Integer departmentId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<TpDepartment>(sysDepartmentService.getById(departmentId));
    }

    /**
     * 更新组织机构
     * @param org 组织机构信息
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult update(TpDepartment org){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysDepartmentService.update(org));
    }

    /**
     * 删除组织机构
     * @param departmentId 组织机构id
     * */
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult delete(Integer departmentId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysDepartmentService.deleteById(departmentId));
    }

    /**
     * 添加组织机构
     * @param org 组织机构信息
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult add(TpDepartment org){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Boolean>(sysDepartmentService.add(org));
    }

}
