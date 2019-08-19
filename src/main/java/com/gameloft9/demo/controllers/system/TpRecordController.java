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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/record")
public class TpRecordController {
    @Autowired
    TpRecordService tpRecordServiceImpl;
    @Autowired
    TpStandardService tpStandardServiceImpl;
    @Autowired
    TpSubstandardService tpSubstandardServiceImpl;
    @Autowired
    TpBigTitleService tpBigTitleServiceImpl;
    @Autowired
    TpSubtitleContentService tpSubtitleContentServiceImpl;
    @Autowired
    TpRelationshipService tpRelationshipServiceImpl;
    @Autowired
    TpPhotoUserService tpPhotoUserServiceImpl;
    @Autowired
    TpVerificationService tpVerificationServiceImpl;

    /*
     * 获取所有角色列表
     * @param page 页序
     * @param limit 分页大小
     * */
    @RequestMapping(value = "/list.do",method = RequestMethod.POST)
    @ResponseBody
    //@BizOperLog(operType = OperType.Query,memo = "获取所有角色列表")
    public IResult getdemorList(String page,String limit){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<TpRecord>>(tpRecordServiceImpl.selectAll(page,limit),tpRecordServiceImpl.countGetAll());
    }
    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加用户")
    public IResult addbig(@RequestBody TpBigTitle big){
        //返回json至前端的均返回ResultBean或者PageResultBean
         int bigTitleId = tpRecordServiceImpl.addbig(big);//添加单表
        for (TpSubtitleContent tpSubtitleContent : big.getList()) {//取list里面值
            tpSubtitleContent.setBigTitleId(big.getBigTitleId());//取id
            tpRecordServiceImpl.addsubtitle(tpSubtitleContent);
            //获取uid
            for (TpRelationship tprelationship:tpSubtitleContent.getBeice()){
                tprelationship.setSubtitleId( tpSubtitleContent.getSubtitleId());
                tpRecordServiceImpl.insertpralation(tprelationship);
            }
            for (TpStandard tpStandard : tpSubtitleContent.getBz_coll()) {
                tpStandard.setSubtitleId(tpSubtitleContent.getSubtitleId());
                tpRecordServiceImpl.addstandard(tpStandard);
                for (TpSubstandard tpSubstandard : tpStandard.getZbz_coll()) {
                    tpSubstandard.setStandardId(tpStandard.getStandardId());
                    tpRecordServiceImpl.addsubstandard(tpSubstandard);
                }
            }
        }
        Date date = new Date();//获取时间
        TpRecord record = new TpRecord(String.valueOf(big.getBigTitleId()),date,"35");//添加并将数据库中INT类型转成String
        tpRecordServiceImpl.insert(record);
        //添加页面过来的数组
        List<String> coll= big.getColl();
        for (String coll1 : coll) {
            TpVerification ver = new TpVerification(coll1,record.getRecordId(),"1",46);
            tpVerificationServiceImpl.addVerification(ver);
        }
        return new ResultBean<String>("success");
    }
    @RequestMapping(value = "/delete.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "删除记录")
    public IResult delete(Integer recordId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        int bigTitleId = tpRecordServiceImpl.selectByrecId(recordId);
        List<TpSubtitleContent> tpSubtitleContent = tpSubtitleContentServiceImpl.findBybigTitleId(bigTitleId);
        for (TpSubtitleContent subtitleContent : tpSubtitleContent) {
            List<TpStandard> tpStandard = tpStandardServiceImpl.findBysubtitleId(subtitleContent.getSubtitleId());
            for (TpStandard standard : tpStandard) {
                //刪除統計表
                List<TpSubstandard> tpSubstandards = tpSubstandardServiceImpl.findBystandardId(standard.getStandardId());
                for (TpSubstandard tpSubstandard:tpSubstandards){
                    tpRecordServiceImpl.deletesubstandardId(tpSubstandard.getSubstandardId());
                }
                tpSubstandardServiceImpl.delete(standard.getStandardId());
            }
            tpRelationshipServiceImpl.delete(subtitleContent.getSubtitleId());
            tpStandardServiceImpl.delete(subtitleContent.getSubtitleId());
        }
        tpSubtitleContentServiceImpl.delete(bigTitleId);
        tpRecordServiceImpl.delver(recordId);
        tpRecordServiceImpl.delete(recordId);
        tpBigTitleServiceImpl.delete(bigTitleId);
        return new ResultBean<String>("success");
    }
    @RequestMapping(value = "/guanbi.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "切换投票模式")
    public IResult guanRe(Integer recordId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Integer>(tpRecordServiceImpl.guanbi(recordId));
    }
    /*
     * 获取角色
     */
    @RequestMapping(value = "/get.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult getRe(Integer recordId){
        //返回json至前端的均返回ResultBean或者PageResultBean
        int bigTitleId = tpRecordServiceImpl.selectByrecId(recordId); //根据主键获取大标题表的外键
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
                for (TpSubstandard tpSubstandard:tpSubstandards){
                    int substandardId = tpSubstandard.getSubstandardId();
                    int sum = tpRecordServiceImpl.findbysid(substandardId);
                    tpSubstandard.setSum(sum);
                }
                standard.setZbz_coll(tpSubstandards);
            }

            subtitleContent.setBeice(tprelationship);
            subtitleContent.setBz_coll(tpStandard);
        }
        TpBigTitle tpBigtitle = tpBigTitleServiceImpl.findbybigid(bigTitleId);
        tpBigtitle.setList(tpSubtitleContent);
        return new ResultBean<TpBigTitle>(tpBigtitle);
    }
    //查看提交
    @RequestMapping(value = "/submit.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult tijiao(@RequestBody List<TpSubtitleId> tpSubtitleId) {
        for (TpSubtitleId tpSubtitleId1:tpSubtitleId) {
            tpRecordServiceImpl.insertsubtitletid(tpSubtitleId1);
            String content =  tpSubtitleId1.getContent();
            tpVerificationServiceImpl.deleteVer(content);
            }
        return new ResultBean<String>("success");
        }
    //校验
    @RequestMapping(value = "/jiaoyan.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "校验")
    public IResult jiaoyan(@RequestBody TpVerification tpVerification){
        String content = tpVerification.getContent();
        TpVerification ver = tpVerificationServiceImpl.findbycontent(content);
        if(ver==null){//判断数据库是否有这个账号
            return new ResultBean<Boolean >(false);
        }
        else {//判断是否是同一次投票，根据发布的id进行判断
            int recordId = tpVerification.getRecordId();
            int recordId1 = ver.getRecordId();
            if(recordId==recordId1){
                return new ResultBean<Boolean >(true);
            }
            else {
                return new ResultBean<Boolean >(false);
            }
        }
    }
}
