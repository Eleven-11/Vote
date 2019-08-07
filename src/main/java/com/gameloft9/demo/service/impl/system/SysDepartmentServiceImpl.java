package com.gameloft9.demo.service.impl.system;

import com.gameloft9.demo.dataaccess.dao.system.SysOrganizeTestMapper;
import com.gameloft9.demo.dataaccess.dao.system.TpDepartmentMapper;
import com.gameloft9.demo.dataaccess.model.system.SysOrganizeTest;
import com.gameloft9.demo.dataaccess.model.tp.TpDepartment;
import com.gameloft9.demo.mgrframework.utils.CheckUtil;
import com.gameloft9.demo.service.api.system.SysDepartmentService;
import com.gameloft9.demo.service.api.system.SysOrgService;
import com.gameloft9.demo.dataaccess.model.tp.OrgNodeResponse;
import com.gameloft9.demo.utils.Constants;
import com.gameloft9.demo.utils.NumberUtil;
import com.gameloft9.demo.utils.UUIDUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 组织机构服务实现类
 * Created by gameloft9 on 2017/12/19.
 */
@Slf4j
@Service
public class SysDepartmentServiceImpl implements SysDepartmentService {

    @Autowired
    TpDepartmentMapper tpDepartmentMapper;

    /**
     * 获取所有组织机构
     */
    public List<OrgNodeResponse> getAll() {
        List<TpDepartment> orgList = new LinkedList<TpDepartment>();
        orgList = tpDepartmentMapper.getAll();

        //转换成树
        return convert2TreeNodes(orgList);
    }
    /**
     * 根据id获取组织机构
     * @param departmentId 主键
     * */
    public TpDepartment getById(Integer departmentId) {
        CheckUtil.notBlank(departmentId+"","组织机构id为空");

        return tpDepartmentMapper.selectByPrimaryKey(departmentId);
    }


    /**
     * 更新组织机构
     * @param department 组织机构信息
     * */
    public Boolean update(TpDepartment department){
        CheckUtil.notNull(department,"组织机构为空");
        CheckUtil.notBlank(department.getDepartmentId()+"","组织机构id为空");
        tpDepartmentMapper.updateByPrimaryKeySelective(department);
        return true;
    }


    public Boolean update(SysOrganizeTest org) {
        return null;
    }


    /**
     * 删除组织机构
     * @param departmentId 组织机构id
     * */
    public Boolean deleteById(Integer departmentId){
        CheckUtil.notBlank(departmentId+"","组织机构id为空");

        //含有子机构不能删除
        int count = tpDepartmentMapper.countChildren(departmentId+"");
        CheckUtil.check(count == 0,"含有子机构，不能删除");

        //删除
        tpDepartmentMapper.deleteByPrimaryKey(departmentId);
        return true;
    }

    public Boolean add(SysOrganizeTest org) {
        return null;
    }

    /**
     * 添加组织机构
     * @param department 组织机构信息
     * */
    public Boolean add(TpDepartment department){
        CheckUtil.notNull(department,"组织机构为空");

        //名称不能重复
        TpDepartment d = tpDepartmentMapper.getByName(department.getDepartmentName());
        CheckUtil.check(d == null,"该组织机构已经存在");

        //根机构只能有一个
        if(StringUtils.isBlank(department.getSuperiorDepartment())){
//            log.info("添加根机构");
            TpDepartment root = tpDepartmentMapper.getRoot();
            CheckUtil.check(root == null,"请选择父机构");
            tpDepartmentMapper.insertSelective(department);
            return true;
        }
//        log.info("添加子机构");
        TpDepartment parent = tpDepartmentMapper.selectByPrimaryKey(Integer.valueOf(department.getSuperiorDepartment()));
        int departmentId = parent.getDepartmentId();
        department.setSuperiorDepartment(departmentId+"");
        tpDepartmentMapper.insertSelective(department);
        return true;
    }

    /*************************私有方法区******************************/

    /**
     * 转成树
     *
     * @param orgList 组织机构列表
     */
    private List<OrgNodeResponse> convert2TreeNodes(List<TpDepartment> orgList) {
        CheckUtil.notNull(orgList, "组织机构列表为空");
        CheckUtil.check(!orgList.isEmpty(), "组织机构列表为空");

        List<OrgNodeResponse> res = new LinkedList<OrgNodeResponse>();

        Iterator<TpDepartment> it = orgList.iterator();
        while (it.hasNext()) {
            TpDepartment d = it.next();
            if (StringUtils.isBlank(d.getSuperiorDepartment())) {//拿到根机构
                OrgNodeResponse node = new OrgNodeResponse();
                node.setId(d.getDepartmentId()+"");
                node.setName(d.getDepartmentName());
                node.setHref("#");
                node.setSpread(false);
                node.setChildren(getChildrenNodes(d.getDepartmentId()+"", orgList));
                res.add(node);
            }
        }

        return res;
    }


    /**
     * 递归获取子节点
     *
     * @param superiorDepartment 父节点id
     * @param orgList  组织机构列表
     */
    private List<OrgNodeResponse> getChildrenNodes(String superiorDepartment, List<TpDepartment> orgList) {
        List<OrgNodeResponse> children = new LinkedList<OrgNodeResponse>();

        Iterator<TpDepartment> it = orgList.iterator();
        while (it.hasNext()) {
            TpDepartment t = it.next();
            if (StringUtils.isNotBlank(t.getSuperiorDepartment())&&
                    t.getSuperiorDepartment().equals(superiorDepartment)) {
                OrgNodeResponse node = new OrgNodeResponse();
                node.setId(t.getDepartmentId()+"");
                node.setName(t.getDepartmentName());
                node.setHref("#");
                node.setSpread(false);
                node.setChildren(getChildrenNodes(node.getId(), orgList));
                children.add(node);
            }
        }

        return children;
    }

}
