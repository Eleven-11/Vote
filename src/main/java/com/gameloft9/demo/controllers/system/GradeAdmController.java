package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.GradeAdm;
import com.gameloft9.demo.dataaccess.model.system.GradeRecord;
import com.gameloft9.demo.dataaccess.model.system.GradeTemplet;
import com.gameloft9.demo.dataaccess.model.system.GradeTotal;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.GradeAdmService;
import com.gameloft9.demo.service.api.system.GradeTotalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @author 李文教
 * @date 2019/7/23 15:24
 */
@Slf4j
@Controller
@RequestMapping("/adm")
public class GradeAdmController {
    @Autowired
    GradeAdmService admService;

    @RequestMapping(value = "/admfindAll.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult admAll(String page,String limit){
        List<GradeAdm> all=admService.findAll(page,limit);
        return new PageResultBean<Collection<GradeAdm>>(all,admService.countGetAll());
    }
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除")
    public IResult admDelete(int admId){
        admService.delete(admId);
        return new ResultBean<Boolean>(true);
    }
    @RequestMapping(value = "/findById.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult admFindById(Integer admId){
       GradeAdm adm = admService.findById(admId);
        return new ResultBean<GradeAdm>(adm);
    }
    @RequestMapping(value = "/findById2.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult admFindById2(Integer admId){
        GradeAdm adm = admService.findById2(admId);
        return new ResultBean<GradeAdm>(adm);
    }
    @RequestMapping(value = "/isTop.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult isTop(int admId){
        return new ResultBean<Integer>(admService.isTop(admId));
    }
    @RequestMapping(value = "/noTop.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult noTop(int admId){
        return new ResultBean<Integer>(admService.noTop(admId));
    }

    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult addTotal(@RequestBody GradeTotal gradeTotal){
        int temId=admService.addTotalAdm(gradeTotal);
        GradeAdm adm=new GradeAdm();
        adm.setaTime(new Date());
        adm.setTempletId(temId);
        adm.setTempletTitle(gradeTotal.getTempletTitle());
        adm.setResId(52);
        admService.insertAdm(adm);
        //System.out.println(adm);
        return new ResultBean<String>("success");
    }
}
