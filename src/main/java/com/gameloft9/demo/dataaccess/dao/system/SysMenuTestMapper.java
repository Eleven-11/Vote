package com.gameloft9.demo.dataaccess.dao.system;

import com.gameloft9.demo.dataaccess.model.system.SysMenuTest;
import com.gameloft9.demo.service.beans.system.SysMenuTestExtend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysMenuTestMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU_TEST
     *
     * @mbggenerated Wed Dec 06 18:29:05 CST 2017
     */
    int deleteByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU_TEST
     *
     * @mbggenerated Wed Dec 06 18:29:05 CST 2017
     */
    int insert(SysMenuTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU_TEST
     *
     * @mbggenerated Wed Dec 06 18:29:05 CST 2017
     */
    int insertSelective(SysMenuTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU_TEST
     *
     * @mbggenerated Wed Dec 06 18:29:05 CST 2017
     */
    SysMenuTest selectByPrimaryKey(String id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU_TEST
     *
     * @mbggenerated Wed Dec 06 18:29:05 CST 2017
     */
    int updateByPrimaryKeySelective(SysMenuTest record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table SYS_MENU_TEST
     *
     * @mbggenerated Wed Dec 06 18:29:05 CST 2017
     */
    int updateByPrimaryKey(SysMenuTest record);


    /**
     * 分页获取菜单
     * */
    List<SysMenuTestExtend> getAll(@Param("start") int start,
                                   @Param("end") int end,
                                   @Param("menuName") String menuName,
                                   @Param("menuCode") String menuCode,
                                   @Param("parentMenuId") String parentMenuId);

    /**
     * 获取菜单大小
     * */
    int countGetAll( @Param("menuName") String menuName,
                     @Param("menuCode") String menuCode,
                     @Param("parentMenuId") String parentMenuId);

    /**
     * 获取一级菜单列表
     * */
    List<SysMenuTest> getFirstClassMenus();

    /**
     * 根据菜单名称获取菜单
     * @param menuName 菜单名称
     */
    SysMenuTest getByMenuName(@Param("menuName") String menuName);

    /**
     * 获取一级菜单最大CODE
     * */
    String getMaxCodeOfFirstClass();

    /**
     * 获取二级菜单最大CODE
     * @param parentMenuId 父菜单id
     * */
    String getMaxCodeOfSecondClass(@Param("parentMenuId") String parentMenuId);

    /**
     * 获取子菜单个数
     * @param menuId 父菜单id
     * */
    int getChildrenCount(@Param("menuId") String menuId);

    /**
     * 根据主键获取菜单信息
     * @param id 菜单主键
     * */
    SysMenuTestExtend getById(@Param("id") String id);

    /**
     * 根据后台请求路径获取记录
     * @param requestUrl 后台请求路径
     * */
    SysMenuTest getByRequestUrl(@Param("requestUrl") String requestUrl);
}