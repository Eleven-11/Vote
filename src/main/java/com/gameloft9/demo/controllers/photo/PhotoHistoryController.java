package com.gameloft9.demo.controllers.photo;

import com.gameloft9.demo.dataaccess.model.photo.*;
import com.gameloft9.demo.mgrframework.annotation.BizOperLog;
import com.gameloft9.demo.mgrframework.beans.constant.OperType;
import com.gameloft9.demo.mgrframework.beans.response.IResult;
import com.gameloft9.demo.mgrframework.beans.response.PageResultBean;
import com.gameloft9.demo.mgrframework.beans.response.ResultBean;
import com.gameloft9.demo.service.api.photo.PhotoHistoryService;
import com.gameloft9.demo.service.api.photo.PhotoVoteService;
import com.gameloft9.demo.utils.PropertyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 年: 2019
 * 月: 07
 * 日: 10
 * 小时: 18
 * 分钟: 10
 *
 * @author 严脱兔
 */
@Slf4j
@Controller
@RequestMapping("/photoVote")
public class PhotoHistoryController {
    @Resource
    private PhotoHistoryService photoHistoryServiceImpl;
    @Resource
    private PhotoVoteService photoVoteServiceImpl;

    @RequestMapping(value = "/voteList.do", method = RequestMethod.POST)
    @ResponseBody
    public IResult findAll(String page, String limit) {
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new PageResultBean<Collection<PhotoHistoryTest>>(photoHistoryServiceImpl.findAll(page, limit), photoHistoryServiceImpl.countGetAll());
    }

    /**
     * 查看加统计
     */
    @RequestMapping(value = "/get.do", method = RequestMethod.POST)
    @ResponseBody
    public IResult getVote(Integer hid) {
        //返回json至前端的均返回ResultBean或者PageResultBean
//        return new ResultBean<PhotoVoteTest>(photoHistoryServiceImpl.findById(vid));
        int vid = photoHistoryServiceImpl.findByVid(hid);
        PhotoVoteTest photoVoteTest = photoHistoryServiceImpl.findById(vid);
        List<PhotoTitleTest> photoTitleTests = photoHistoryServiceImpl.findByTid(vid);
        for (PhotoTitleTest photoTitleTest : photoTitleTests) {
            List<PhotoContentTest> optionTitle = photoHistoryServiceImpl.findByCid(photoTitleTest.getTid());
            List<PhotoAnswerTest> answerList = photoHistoryServiceImpl.findByAid(photoTitleTest.getTid());
            photoTitleTest.setOptionTitle(optionTitle);
            photoTitleTest.setAnswerList(answerList);
            for (PhotoAnswerTest photoAnswerTest : answerList) {
                int aid = photoAnswerTest.getAid();
                int count = photoHistoryServiceImpl.count(aid);
                photoAnswerTest.setCount(count);
            }
        }
        photoVoteTest.setList(photoTitleTests);
        return new ResultBean<PhotoVoteTest>(photoVoteTest);

    }


    /**
     * 删除投票
     */
    @RequestMapping(value = "/delete.do", method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE, memo = "删除投票")
    public IResult deleteUser(int hid) {
        //返回json至前端的均返回ResultBean或者PageResultBean
        int vid = photoHistoryServiceImpl.findByVid(hid);
        java.util.List<PhotoTitleTest> photoTitleTests = photoHistoryServiceImpl.findByTid(vid);
            photoHistoryServiceImpl.deleteVer(hid);
            photoHistoryServiceImpl.deleteSum(hid);
        for (PhotoTitleTest photoTitleTest : photoTitleTests) {
            List<PhotoAnswerTest> photoAnswerTests = photoHistoryServiceImpl.findByAid(photoTitleTest.getTid());
            for (PhotoAnswerTest photoAnswerTest : photoAnswerTests) {
                photoHistoryServiceImpl.deleteAnswer(photoAnswerTest.getTid());
            }
            photoHistoryServiceImpl.deleteContent(photoTitleTest.getTid());

        }
        photoHistoryServiceImpl.deleteTitle(vid);
        photoHistoryServiceImpl.delete(vid);
        photoHistoryServiceImpl.deleteVote(vid);
        return new ResultBean<Boolean>();
    }

    /**
     * 关闭投票
     */
    @RequestMapping(value = "/cancel.do", method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.DELETE, memo = "关闭投票")
    public IResult cancelUser(int hid) {
        //返回json至前端的均返回ResultBean或者PageResultBean
        return new ResultBean<Integer>(photoHistoryServiceImpl.cancel(hid));
    }

    /**
     * 创建图片投票和存账号
     */
    @RequestMapping(value = "/votePhoto.do", method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD, memo = "创建图片投票和存账号")
    public IResult addVote(@RequestBody PhotoVoteTest photoVoteTest) {
        int vid = photoVoteServiceImpl.addVote(photoVoteTest);
        Date date = new Date();
        PhotoHistoryTest photoHistoryTest = new PhotoHistoryTest(String.valueOf(photoVoteTest.getVid()), date, "35");
        photoVoteServiceImpl.addHistory(photoHistoryTest);
        for (PhotoTitleTest photoTitleTest : photoVoteTest.getList()) {
            photoTitleTest.setVid(photoVoteTest.getVid());
            photoVoteServiceImpl.addTitle(photoTitleTest);
            for (PhotoContentTest photoContentTest : photoTitleTest.getOptionTitle()) {
                photoContentTest.setTid(photoTitleTest.getTid());
                photoVoteServiceImpl.addContent(photoContentTest);
            }
            for (PhotoAnswerTest photoAnswerTest : photoTitleTest.getAnswerList()) {
                photoAnswerTest.setTid(photoTitleTest.getTid());
                photoVoteServiceImpl.addAnswer(photoAnswerTest);
            }
        }
        List<String> accounts = photoVoteTest.getAccounts();
        for (String content : accounts) {
            //1  未投票    0已投票
            String isVote = "1";
            String id = "47";
            VerificationQueTest verificationQueTest = new VerificationQueTest(id, content, photoHistoryTest.getHid(), isVote);
            photoHistoryServiceImpl.addverificationQue(verificationQueTest);
        }
        return new ResultBean<String>("success");
//          return new ResultBean<String>("success");


    }

    /**
     *   提交
      */
    @RequestMapping(value = "/voteSuccess.do", method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD, memo = "提交")
    public IResult addSumbit(@RequestBody PhotoSumTest photoSumTest) {
        List<String> aids = photoSumTest.getAids();
        for (String aid : aids) {
            PhotoSumTest photosumTest = new PhotoSumTest(aid,photoSumTest.getHid());
            photoHistoryServiceImpl.addSumbit(photosumTest);
        }
        photoHistoryServiceImpl.deleteVerification(photoSumTest.getContent());
        return new ResultBean<String>("success");
    }



    /**
     *   校验
     */
    @RequestMapping(value = "/verification.do", method = RequestMethod.POST)
    @ResponseBody
    @BizOperLog(operType = OperType.ADD, memo = "校验")
    public IResult verification(@RequestBody VerificationQueTest verificationQueTest) {
        String content = verificationQueTest.getContent();
        VerificationQueTest ver = photoHistoryServiceImpl.verification(content);
        if(ver==null){
            return new ResultBean<Boolean>(false);

        }else {
            int recordId = verificationQueTest.getRecordId();
            int recordId1 = ver.getRecordId();
            if (recordId == recordId1) {
                return new ResultBean<Boolean>(true);

            } else {
                return new ResultBean<Boolean>(false);
            }
        }
    }

    /**
     * 上传图片
     */
    @RequestMapping(value = "/uploadphoto")
    @ResponseBody
    public IResult upload(MultipartFile file, String type, String fileName) throws Exception {
//        System.out.println(file);
//        file.transferTo(new File(PropertyUtil.getProperty("file_root_path"),file.getOriginalFilename()));
//        ArrayList<String> list = new ArrayList<String>();
//            System.out.println(f.getOriginalFilename());
        file.transferTo(new File(PropertyUtil.getProperty("file_root_path"), file.getOriginalFilename()));
        System.out.println(PropertyUtil.getProperty("web_base_url") + file.getOriginalFilename());
        //每张图的路径拼接好返回
        //返回json至前端的均返回ResultBean或者PageResultBean
//        System.out.println(file.getOriginalFilename());
//        System.out.println(PropertyUtil.getProperty("web_base_url"));
//        System.out.println(PropertyUtil.getProperty("file_root_path"));
//        return new ResultBean<String>(FileUtil.saveAndReturnUrl(file.getBytes(), Constants.AttachmentType.USER_FACE.value,fileName, PropertyUtil.getProperty("web_base_url"),PropertyUtil.getProperty("file_root_path")));
        return new ResultBean<String>(PropertyUtil.getProperty("web_base_url") + file.getOriginalFilename());
    }

}

