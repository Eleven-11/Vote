package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.*;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.Date;
import java.util.List;
@Service
@Controller
@Slf4j
@RequestMapping("/template")
public class TpTemplateController {
    @Autowired
    TpTemplateService tpTemplateServiceImpl;
    @Autowired
    TpStandardService tpStandardServiceImpl;
    @Autowired
    TpSubstandardService tpSubstandardServiceImpl;
    @Autowired
    TpBigTitleService tpBigTitleServiceimpl;
    @Autowired
    TpSubtitleContentService tpSubtitleContentServiceImpl;
    @Autowired
    TpRelationshipService tpRelationshipServiceImpl;
    @Autowired
    TpPhotoUserService tpPhotoUserServiceImpl;


    @RequestMapping(value = "/list.do", method = RequestMethod.POST)
    @ResponseBody
    public IResult alltemp(String page, String limit) {
        //返回json至前端的均返回ResultBean或者PageResultBean
        List<TpTemplate> data = tpTemplateServiceImpl.alltemp(page, limit);
        return new ResultBean<Collection<TpTemplate>>(data);
    }

    @RequestMapping(value = "/add.do", method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD, memo = "添加模板")
    public IResult addbig(@RequestBody TpBigTitle big) {
        //返回json至前端的均返回ResultBean或者PageResultBean
        int bigTitleId = tpTemplateServiceImpl.addbig(big);//添加单表大标题
        for (TpSubtitleContent tpSubtitleContent : big.getList()) {//取list里面值 子标题
            tpSubtitleContent.setBigTitleId(big.getBigTitleId());//取id
            tpTemplateServiceImpl.addsubtitle(tpSubtitleContent);

            for (TpRelationship tprelationship:tpSubtitleContent.getBeice()){
                tprelationship.setSubtitleId( tpSubtitleContent.getSubtitleId());
                tpTemplateServiceImpl.insertpralation(tprelationship);
            }
            for (TpStandard tpStandard : tpSubtitleContent.getBz_coll()) {//标准
                tpStandard.setSubtitleId(tpSubtitleContent.getSubtitleId());
                tpTemplateServiceImpl.addstandard(tpStandard);
                for (TpSubstandard tpSubstandard : tpStandard.getZbz_coll()) {//子标准
                    tpSubstandard.setStandardId(tpStandard.getStandardId());
                    tpTemplateServiceImpl.addsubstandard(tpSubstandard);
                }
            }
        }
        Date date = new Date();//获取时间
        TpTemplate template = new TpTemplate(big.getBigTitleId(),date,"52");//添加并将数据库中INT类型转成String
        template.setTemplateTitleId(big.getTemplateTitleId());
        tpTemplateServiceImpl.inserttem(template);
        return new ResultBean<String>("success");
    }
    @RequestMapping(value = "/qxzd.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "取消置顶")
    public IResult qxzdRe(String templateTitleId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Integer>(tpTemplateServiceImpl.qxzd(templateTitleId));
    }
    @RequestMapping(value = "/zd.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "置顶")
    public IResult zdRe(String templateTitleId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Integer>(tpTemplateServiceImpl.zd(templateTitleId));
    }
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD, memo = "s删除模块")
    public IResult deletebig(int templateId) {
        //返回json至前端的均返回ResultBean或者PageResultBean
        int bigTitleId = tpTemplateServiceImpl.selectById(templateId);
        List<TpSubtitleContent> tpSubtitleContent = tpSubtitleContentServiceImpl.findBybigTitleId(bigTitleId);
        for (TpSubtitleContent subtitleContent : tpSubtitleContent) {
            List<TpStandard> tpStandard = tpStandardServiceImpl.findBysubtitleId(subtitleContent.getSubtitleId());
            for (TpStandard standard : tpStandard) {
                tpSubstandardServiceImpl.delete(standard.getStandardId());
            }
            tpRelationshipServiceImpl.delete(subtitleContent.getSubtitleId());
            tpStandardServiceImpl.delete(subtitleContent.getSubtitleId());
        }
        tpSubtitleContentServiceImpl.delete(bigTitleId);
        tpTemplateServiceImpl.delete(templateId);
        tpBigTitleServiceimpl.delete(bigTitleId);

        return new ResultBean<String>("success");
    }
    /*
     * 获取角色
     */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getRe(Integer templateId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        int bigTitleId = tpTemplateServiceImpl.selectById(templateId);//根据主键获取大标题表的外键

        List<TpSubtitleContent> tpSubtitleContent = tpSubtitleContentServiceImpl.findBybigTitleId1(bigTitleId);//根据子标题外键查询子标题内容
        for (TpSubtitleContent subtitleContent : tpSubtitleContent) {//根据标准的外键遍历子标题中的list
            List<TpStandard> tpStandard = tpStandardServiceImpl.findBysubtitleId1(subtitleContent.getSubtitleId());
                List<TpRelationship> tprelationship = tpRelationshipServiceImpl.findBysId(subtitleContent.getSubtitleId());

            for (TpRelationship tprelationship1 : tprelationship) {
                TpPhotoUser tpphotoUser = tpPhotoUserServiceImpl.findbyuid(Integer.parseInt(tprelationship1.getUid()));
                tprelationship1.setTpphotoUser(tpphotoUser);
            }
            for (TpStandard standard : tpStandard) {//根据子标准的外键查询标准中的list
                List<TpSubstandard> tpSubstandards = tpSubstandardServiceImpl.findBystandardId1(standard.getStandardId());
                standard.setZbz_coll(tpSubstandards);
            }

            subtitleContent.setBeice(tprelationship);
            subtitleContent.setBz_coll(tpStandard);
        }
        TpBigTitle tpBigtitle = tpBigTitleServiceimpl.findbybigid(bigTitleId);
        tpBigtitle.setList(tpSubtitleContent);
        return new ResultBean<TpBigTitle>(tpBigtitle);
    }

}
