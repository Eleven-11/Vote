package com.gameloft9.demo.controllers.system;

import com.gameloft9.demo.dataaccess.model.system.TpDemVerification;
import com.gameloft9.demo.dataaccess.model.system.TpGradeStatistics;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.system.TpGradeRecordService;
import com.gameloft9.demo.service.api.system.TpGradeStatisticsService;
import com.gameloft9.demo.service.api.system.TpGradeTempletService;
import com.gameloft9.demo.service.beans.system.TpGradeMuBan;
import com.gameloft9.demo.service.beans.system.TpGradePingCe;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Auther: 帅气的达
 * @Date: 2019/7/17 14:56
 * @Description:
 */
@Controller
@Slf4j
@RequestMapping("/pingce")
public class TpGradeTempletController {

    @Autowired
    TpGradeTempletService tpGradeTempletService;

    @Autowired
    TpGradeRecordService tpGradeRecordService;

    @Autowired
    TpGradeStatisticsService tpGradeStatisticsService;

    String []uids;

    @RequestMapping(value = "/add.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加标题、人员等", memos = "2")
    public IResult add(TpGradePingCe tpGradePingCe){

        tpGradeTempletService.add(tpGradePingCe.getTitle(), tpGradePingCe.getExplains(), tpGradePingCe.getPeonum());
        Integer recordId = tpGradeTempletService.addrub(tpGradePingCe.getTmarr(), tpGradePingCe.getDaarr(), tpGradePingCe.getTm());
        tpGradeTempletService.addeva(uids);

        return new ResultBean<Integer>(recordId);
    }

    @RequestMapping(value = "/addry.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加人员", memos = "9")
    public IResult addry(@RequestParam(value="uid") String []uid){

        uids = new String[uid.length];
        for (int i = 0; i < uid.length; i++) {
            uids[i] = uid[i];
        }

        return new ResultBean<String>("success");
    }

    @RequestMapping(value = "/addmuban.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加模板、人员等", memos = "17")
    public IResult addmuban(TpGradeMuBan tpGradeMuBan){

        tpGradeTempletService.add(tpGradeMuBan.getTitle(), tpGradeMuBan.getExplains(), tpGradeMuBan.getPeonum());
        tpGradeTempletService.addmuban(tpGradeMuBan.getTemplet_title());
        tpGradeTempletService.addmub(tpGradeMuBan.getTmarr(), tpGradeMuBan.getDaarr(), tpGradeMuBan.getTm());
        tpGradeTempletService.addeva(uids);

        return new ResultBean<String>("success");
    }

    @RequestMapping(value = "/findzh.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult findzh(){

        List<TpDemVerification> list = tpGradeTempletService.findzh();
        return new ResultBean<List<TpDemVerification>>(list);

    }

    @RequestMapping(value = "/bcmuban.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.UPDATE,memo = "保存模板", memos = "7")
    public IResult bcmuban(TpGradeMuBan tpGradeMuBan){

        tpGradeTempletService.bcmuban(tpGradeMuBan);
        tpGradeTempletService.add(tpGradeMuBan.getTitle(), tpGradeMuBan.getExplains(), tpGradeMuBan.getPeonum());
        tpGradeTempletService.addmuban(tpGradeMuBan.getTemplet_title());
        tpGradeTempletService.addmub(tpGradeMuBan.getTmarr(), tpGradeMuBan.getDaarr(), tpGradeMuBan.getTm());
        tpGradeTempletService.addeva(uids);
        return new ResultBean<String>("success");
    }

    @RequestMapping(value = "/tongji.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD,memo = "添加统计数据", memos = "3")
    public IResult tongji(String []answer_Id, String []uid, Integer templet_Id){

        int a = 0;
        Integer recId = tpGradeRecordService.findRecId(templet_Id);
        for (int i = 0; i < uid.length; i++) {
            for (int j = 0; j < (answer_Id.length/uid.length); j++) {
                TpGradeStatistics tpGradeStatistics = new TpGradeStatistics(recId, Integer.parseInt(answer_Id[a]), Integer.parseInt(uid[i]));
                tpGradeStatisticsService.addSta(tpGradeStatistics);
                a++;
            }
        }
        return new ResultBean<String>("success");
    }

    @RequestMapping(value = "/loadry.do",method = RequestMethod.POST)
    @ResponseBody
    public IResult loadry(){
        return new ResultBean<String []>(uids);
    }

    @RequestMapping(value = "/getzh.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.Query,memo = "查看投票账号", memos = "16")
    public IResult getzh(Integer record_Id){

        List<TpDemVerification> list = tpGradeTempletService.getzh(record_Id);
        return new ResultBean<List<TpDemVerification>>(list);

    }

    @RequestMapping(value = "/delzh.do",method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE,memo = "投票完成", memos = "31")
    public IResult delzh(String content){

        tpGradeTempletService.delzh(content);
        return new ResultBean<String>("success");
    }

}
