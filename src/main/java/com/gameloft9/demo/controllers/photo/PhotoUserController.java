package com.gameloft9.demo.controllers.photo;

import com.gameloft9.demo.dataaccess.model.photo.PhotoDepartmentTest;
import com.gameloft9.demo.dataaccess.model.photo.PhotoUserTest;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.photo.PhotoUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

/**
 * 系统菜单controller
 * Created by gameloft9 on 2017/12/4.
 */
@Controller
@Slf4j
@RequestMapping("/photoUser")
public class PhotoUserController {
    @Autowired
    private PhotoUserService photoUserServiceImpl;

    /**
     * 获取所有菜单列表
     * @param page 页序
     * @param limit 分页大小
     *  3.找到后台处理的控制器类
     * */
    @RequestMapping(value = "/photoUserList.do",method = RequestMethod.POST)
    @ResponseBody
    //@BizOperLog(operType = OperType.Query,memo = "获取所有菜单列表")
    public IResult findAll(String page, String limit,String uname, Integer departmentId, Integer classOfPosition, Integer position, Integer gradeOfJudge, Integer category){
        //1.接收参数
        //2.调用servie
        //3.返回结果(json数据)
        //返回json至前端的均返回ResultBean或者PageResultBean
        //PageResultBean<List<Student>>
        return new PageResultBean<Collection<PhotoUserTest>>(photoUserServiceImpl.findAll(page,limit,uname,departmentId,classOfPosition,position,gradeOfJudge,category),photoUserServiceImpl.countGetAll());
    }

    /**
     * 添加用户
     * */
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加用户")
    public IResult addStudent(@RequestBody PhotoUserTest photoUserTest){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(photoUserServiceImpl.addUser(photoUserTest));
    }

    /**
     * 删除用户
     * */
    @RequestMapping(value = "/deleteUser.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除用户")
    public IResult deleteUser(int uid){
        //返回json至前端的均返回ResultBean或者PageResultBean
        System.out.println("11111");
        return new ResultBean<Integer>(photoUserServiceImpl.deleteUser(uid));
    }

    /**
     * 获取用户
     * */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getMenu(int uid){
        //返回json至前端的均返回ResultBean或者PageResultBean
        System.out.println("1111");
        return new ResultBean<PhotoUserTest>(photoUserServiceImpl.findById(uid));
    }

    /**
     * 获取部门
     * */
    @RequestMapping(value = "/find.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(){
        //返回json至前端的均返回ResultBean或者PageResultBean

        return new ResultBean<Collection<PhotoDepartmentTest>>( photoUserServiceImpl.findDepartment());
    }

    /**
     * 更新用户
     * */
    @RequestMapping(value = "/update.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "更新用户")
    public IResult updateMenu(@RequestBody PhotoUserTest photoUserTest){//传递了数组，前台放在payload里面了，后台通过@RequestBody获取
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<String>(photoUserServiceImpl.update(photoUserTest));
    }
    /**
     * 批量上传
     * */
    @ResponseBody
    @RequestMapping(value="/fileUpload.do")
    public String UploadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return photoUserServiceImpl.ajaxUploadExcel(request, response);
    }
}
