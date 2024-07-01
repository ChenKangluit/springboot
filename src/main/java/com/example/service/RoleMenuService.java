package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.RoleMenu;

import java.util.List;


public interface RoleMenuService extends IService<RoleMenu> {


    /**
     * 分配权限
     * @param roleId
     * @param menuIds
     */
    Boolean setRoleMenu(Integer roleId, List<Integer> menuIds);

    /**
     * 获取权限菜单
     * @param roleId
     * @return
     */
    List<Integer> getRoleMenu(Integer roleId);
}
