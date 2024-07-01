package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.RoleMenu;
import com.example.mapper.RoleMenuMapper;
import com.example.service.MenuService;
import com.example.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    /**
     *
     * @param roleId
     * @param menuIds
     */
    @Transactional
    @Override
    public Boolean setRoleMenu(Integer roleId, List<Integer> menuIds) {
       //先删除 权限id
        LambdaQueryWrapper<RoleMenu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RoleMenu::getRoleId,roleId);
        roleMenuMapper.delete(wrapper);
        //再添加
        for (Integer menuId : menuIds) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
        return true;
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }
}
