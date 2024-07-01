package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.Result;
import com.example.entity.SysPermission;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lay
 * @since 2022-04-17
 */
public interface SysPermissionService extends IService<SysPermission> {

    /**
     * 二级菜单
     * @param name
     * @return
     */
    List<SysPermission> findSysPermissions(String name);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    Boolean removeSysPermission(Integer id);

    /**
     * 查询权限对应菜单
     * @return
     */
    List<SysPermission> getPermissionList(Integer pid);

    /**
     * 新增菜单
     * @param sysPermission
     * @return
     */
    Result saveData(SysPermission sysPermission);

    /**
     * 新增菜单
     * @param id
     * @return
     */
    Result deleteById(Integer id);


}
