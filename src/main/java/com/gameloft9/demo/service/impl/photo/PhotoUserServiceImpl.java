package com.gameloft9.demo.service.impl.photo;

import com.gameloft9.demo.dataaccess.dao.photo.PhotoUserTestMapper;
import com.gameloft9.demo.dataaccess.model.photo.ExcelUtil;
import com.gameloft9.demo.dataaccess.model.photo.PhotoDepartmentTest;
import com.gameloft9.demo.dataaccess.model.photo.PhotoUserTest;
import com.gameloft9.demo.service.api.photo.PhotoUserService;
import com.gameloft9.demo.service.beans.system.PageRange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by gameloft9 on 2017/12/6.
 */
@Slf4j
@Service
public class PhotoUserServiceImpl implements PhotoUserService {
    @Autowired
    private PhotoUserTestMapper photoUserTestMapper;


    public int deleteUser(int uid) {
        photoUserTestMapper.deleteUser(uid);
        return 1;
    }

    //批量导入
    public boolean insert(PhotoUserTest photoUserTest) {
        return photoUserTestMapper.insert(photoUserTest);
    }

    public String ajaxUploadExcel(HttpServletRequest request,
                                  HttpServletResponse response) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

        MultipartFile file = multipartRequest.getFile("upfile");
        if(file.isEmpty()){
            try {
                throw new Exception("文件不存在！");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        InputStream in =null;
        try {
            in = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<List<Object>> listob = null;
        try {
            listob = new ExcelUtil().getBankListByExcel(in,file.getOriginalFilename());
        } catch (Exception e) {
            e.printStackTrace();
        }

        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出
        for (int i = 0; i < listob.size(); i++) {
            List<Object> lo = listob.get(i);
            PhotoUserTest vo = new PhotoUserTest();
            /*这里是主键验证，根据实际需要添加，可要可不要，加上之后，可以对现有数据进行批量修改和导入
            User j = null;
			try {
				j = userMapper.selectByPrimaryKey(Integer.valueOf(String.valueOf(lo.get(0))));
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				System.out.println("没有新增");
			}*/
            //vo.setUserId(Integer.valueOf(String.valueOf(lo.get(0))));  // 刚开始写了主键，由于主键是自增的，又去掉了，现在只有批量插入的功能，不能对现有数据进行修改了
            vo.setUname(String.valueOf(lo.get(0)));     // 表格的第一列   注意数据格式需要对应实体类属性
           //性别
           if(String.valueOf(lo.get(1)).equals("男")){
               vo.setSex(33+"");
           }else if(String.valueOf(lo.get(1)).equals("女")){
               vo.setSex(34+"");
           }
           // vo.setSex(String.valueOf(lo.get(1)));   // 表格的第二列
            //部门ID
            List<PhotoDepartmentTest> selectdid = photoUserTestMapper.selectdid();
            for (int d = 0; d < selectdid.size(); d++) {
                if (String.valueOf(lo.get(2)).equals(selectdid.get(d).getDepartmentName())){
                    vo.setDepartmentId(selectdid.get(d).getDepartmentId()+"");
                }
            }

            //vo.setDepartmentId(String.valueOf(lo.get(2)));   // 表格的第三列
            //职务
            if(String.valueOf(lo.get(3)).equals("科员")){
                vo.setClassOfPosition(1+"");
            }else if(String.valueOf(lo.get(3)).equals("副科")){
                vo.setClassOfPosition(2+"");
            }else if(String.valueOf(lo.get(3)).equals("正科")){
                vo.setClassOfPosition(3+"");
            }else if(String.valueOf(lo.get(3)).equals("副处")){
                vo.setClassOfPosition(4+"");
            }else if(String.valueOf(lo.get(3)).equals("正处")){
                vo.setClassOfPosition(5+"");
            }else if(String.valueOf(lo.get(3)).equals("副局")){
                vo.setClassOfPosition(6+"");
            }
            //vo.setClassOfPosition(String.valueOf(lo.get(3)));   // 表格的第四列
            if(String.valueOf(lo.get(4)).equals("书记员")){
                vo.setPosition(7+"");
            }else if(String.valueOf(lo.get(4)).equals("法官助理")){
                vo.setPosition(8+"");
            }else if(String.valueOf(lo.get(4)).equals("员额法官")){
                vo.setPosition(9+"");
            }else if(String.valueOf(lo.get(4)).equals("科员")){
                vo.setPosition(10+"");
            }else if(String.valueOf(lo.get(4)).equals("副主任科员")){
                vo.setPosition(11+"");
            }else if(String.valueOf(lo.get(4)).equals("主任科员")){
                vo.setPosition(12+"");
            }else if(String.valueOf(lo.get(4)).equals("院领导")){
                vo.setPosition(13+"");
            }else if(String.valueOf(lo.get(4)).equals("庭长")){
                vo.setPosition(14+"");
            }else if(String.valueOf(lo.get(4)).equals("副庭长")){
                vo.setPosition(15+"");
            }else if(String.valueOf(lo.get(4)).equals("院长")){
                vo.setPosition(16+"");
            }else if(String.valueOf(lo.get(4)).equals("副院长")){
                vo.setPosition(17+"");
            }else if(String.valueOf(lo.get(4)).equals("速录员")){
                vo.setPosition(18+"");
            }else if(String.valueOf(lo.get(4)).equals("协警")){
                vo.setPosition(19+"");
            }else if(String.valueOf(lo.get(4)).equals("职工")){
                vo.setPosition(20+"");
            }
            //vo.setPosition(String.valueOf(lo.get(4)));   // 表格的第五列
            if(String.valueOf(lo.get(5)).equals("一级高级法官")){
                vo.setGradeOfJudge(21+"");
            }else if(String.valueOf(lo.get(5)).equals("二级高级法官")){
                vo.setGradeOfJudge(22+"");
            }else if(String.valueOf(lo.get(5)).equals("三级高级法官")){
                vo.setGradeOfJudge(23+"");
            }else if(String.valueOf(lo.get(5)).equals("四级高级法官")){
                vo.setGradeOfJudge(24+"");
            }else if(String.valueOf(lo.get(5)).equals("一级法官")){
                vo.setGradeOfJudge(25+"");
            }else if(String.valueOf(lo.get(5)).equals("二级法官")){
                vo.setGradeOfJudge(26+"");
            }else if(String.valueOf(lo.get(5)).equals("三级法官")){
                vo.setGradeOfJudge(27+"");
            }else if(String.valueOf(lo.get(5)).equals("四级法官")){
                vo.setGradeOfJudge(28+"");
            }
           // vo.setGradeOfJudge(String.valueOf(lo.get(5)));   // 表格的第六列
            if(String.valueOf(lo.get(6)).equals("院领导")){
                vo.setCategory(29+"");
            }else if(String.valueOf(lo.get(6)).equals("中层领导")){
                vo.setCategory(30+"");
            }else if(String.valueOf(lo.get(6)).equals("普通干警")){
                vo.setCategory(31+"");
            }else if(String.valueOf(lo.get(6)).equals("非编人员")){
                vo.setCategory(32+"");
            }
           // vo.setCategory(String.valueOf(lo.get(6)));   // 表格的第七列
            //vo.setRegTime(Date.valueOf(String.valueOf(lo.get(2))));
            photoUserTestMapper.insert(vo);
			/*if(j == null)
			{
		            userMapper.insert(vo);
			}
			else
			{
		            userMapper.updateByPrimaryKey(vo);
			}*/
        }
        System.out.println("文件导入成功！");
        return "success";
    }

    public String addUser(PhotoUserTest photoUserTest) {
        photoUserTestMapper.addUser(photoUserTest);
        return "success";
    }

    public PhotoUserTest findById(int uid) {
        return photoUserTestMapper.findById(uid);
    }

    public String update(PhotoUserTest photoUserTest) {
        photoUserTestMapper.update(photoUserTest);
        return "success";
    }

    public List<PhotoUserTest> findAll(String page, String limit, String uname, Integer departmentId, Integer classOfPosition, Integer position, Integer gradeOfJudge, Integer category) {
        PageRange pageRange = new PageRange(page, limit);
        return photoUserTestMapper.findAll(pageRange.getStart(),pageRange.getEnd(),uname,departmentId,classOfPosition,position,gradeOfJudge,category);
    }

    public List<PhotoDepartmentTest> findDepartment() {
        return photoUserTestMapper.findDepartment();
    }

    public int countGetAll() {
        return photoUserTestMapper.countGetAll();
    }
}
