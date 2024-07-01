package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Result;
import com.example.entity.SysPermission;
import com.example.mapper.SysPermissionMapper;
import com.example.mapper.UserRoleMapper;
import com.example.service.SysPermissionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements SysPermissionService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private SysPermissionMapper sysPermissionMapper;


    @Override
    public List<SysPermission> findSysPermissions(String name) {
        LambdaQueryWrapper<SysPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(!ObjectUtil.isEmpty(name), SysPermission::getName, name);

        List<SysPermission> SysPermissionList = list(wrapper);

        List<SysPermission> parentNode = SysPermissionList.stream().filter(SysPermission -> SysPermission.getPid() == null).collect(Collectors.toList());

        for (SysPermission SysPermission : parentNode) {
            SysPermission.setChildren(SysPermissionList.stream().filter(SysPermission1 -> SysPermission.getId().equals(SysPermission1.getPid())).collect(Collectors.toList()));
        }
        return parentNode;
    }

    @Override
    public Boolean removeSysPermission(Integer id) {
        LambdaQueryWrapper<SysPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysPermission::getId,id)
                .or()
                .eq(SysPermission::getPid,id);
       return remove(wrapper);
    }

    @Override
    public List<SysPermission> getPermissionList(Integer pid){
        QueryWrapper<SysPermission> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pid",0);
        List<SysPermission> menusBase = sysPermissionMapper.selectList(queryWrapper);
        List<SysPermission> menuLNotBase = sysPermissionMapper.selectList(new QueryWrapper<>());
        for (SysPermission menu : menusBase) {
            List<SysPermission> menus = iterateMenus(menuLNotBase, menu.getId());
            menu.setChildren(menus);
        }
        return  menusBase;
    }

    /**
     *多级菜单查询方法
     * @param menuVoList 不包含最高层次菜单的菜单集合
     * @param pid 父类id
     * @return
     */
    public List<SysPermission> iterateMenus(List<SysPermission> menuVoList,Integer pid){
        List<SysPermission> result = new ArrayList<SysPermission>();
        for (SysPermission menu : menuVoList) {
            //获取菜单的id
            Integer menuid = menu.getId();
            //获取菜单的父id
            Integer parentid = menu.getPid();
            if(parentid!=null){
                if(parentid==pid){
                    //递归查询当前子菜单的子菜单
                    List<SysPermission> iterateMenu = iterateMenus(menuVoList,menuid);
                    menu.setChildren(iterateMenu);
                    result.add(menu);
                }
            }
        }
        return result;
    }

    @Override
    public Result saveData(SysPermission sysPermission) {
        Integer result=0;
        Integer id=sysPermission.getId();
        SysPermission newData=sysPermissionMapper.selectById(id);
        if(newData!=null){
            //修改
            sysPermission.setId(id);
            result=sysPermissionMapper.updateById(sysPermission);
        }else{
            //新增
            sysPermission.setId(null);
            result=sysPermissionMapper.insert(sysPermission);
        }
        if(result>0){
            return Result.success("保存成功");
        }else{
            return Result.error(-1,"保存失败");
        }

    }

    @Override
    public Result deleteById(Integer id) {
        Integer result=sysPermissionMapper.deleteById(id);
        if(result>0){
            return Result.success("删除成功");
        }else{
            return Result.error(-1,"删除失败");
        }
    }


}
