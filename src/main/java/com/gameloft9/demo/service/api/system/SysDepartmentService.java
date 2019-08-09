package com.gameloft9.demo.service.api.system;

import com.gameloft9.demo.dataaccess.model.system.SysOrganizeTest;
import com.gameloft9.demo.dataaccess.model.tp.OrgNodeResponse;
import com.gameloft9.demo.dataaccess.model.tp.TpDepartment;

import java.util.List;

/**
 * 组织机构服务
 * Created by gameloft9 on 2017/12/19.
 */
public interface SysDepartmentService {

    /**
     * 获取所有组织机构
     * */
    List<OrgNodeResponse> getAll();

    /**
     * 根据id获取组织机构
     * @param departmentId 主键
     * */
    TpDepartment getById(Integer departmentId);

    /**
     * 更新组织机构
     * @param department 组织机构信息
     * */
    Boolean update(TpDepartment department);

    /**
     * 删除组织机构
     * @param departmentId 组织机构id
     * */
    Boolean deleteById(Integer departmentId);

    /**
     * 添加组织机构
     * @param department 组织机构信息
     * */
    Boolean add(TpDepartment department);

}
