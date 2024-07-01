package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dto.UserDto;
import com.example.entity.*;
import com.example.mapper.MenuMapper;
import com.example.mapper.RoleMenuMapper;
import com.example.mapper.UserRoleMapper;
import com.example.service.MenuService;
import com.example.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMenuMapper roleMenuMapper;


    @Override
    public List<Menu> findMenus(String name) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(!ObjectUtil.isEmpty(name), Menu::getName, name);

        List<Menu> menuList = list(wrapper);

        List<Menu> parentNode = menuList.stream().filter(menu -> menu.getPid() == null).collect(Collectors.toList());

        for (Menu menu : parentNode) {
            menu.setChildren(menuList.stream().filter(menu1 -> menu.getId().equals(menu1.getPid())).collect(Collectors.toList()));
        }
        return parentNode;
    }

    @Override
    public Boolean removeMenu(Integer id) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getId,id)
                .or()
                .eq(Menu::getPid,id);
       return remove(wrapper);
    }

    @Override
    public   List<Menu> getMenuRole(){
        //拿到id
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();

        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,loginUser.getUser().getId());

        List<UserRole> userRoles = userRoleMapper.selectList(wrapper);
        //权限的所有权限id
        List<Integer> collect1 = userRoles.stream().map(UserRole::getRoleId).collect(Collectors.toList());
        //拿到所有权限对应的菜单id
        List<RoleMenu> roleMenus = roleMenuMapper.selectBatchByRoleIds(collect1);
        //所有菜单ID
        List<Integer> menuIds = roleMenus.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());

        //所有菜单
        List<Menu> menuAll = findMenus("");
        //过滤菜单 根据角色id
        List<Menu> roleMenu = new ArrayList<>();
        for (Menu menu : menuAll) {
            if (menuIds.contains(menu.getId())){
                roleMenu.add(menu);
            }
            List<Menu> children = menu.getChildren();
            children.removeIf(child -> !menuIds.contains(child.getId()));
        }
        return roleMenu;
    }


}
