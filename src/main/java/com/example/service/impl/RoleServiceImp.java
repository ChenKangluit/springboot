package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.entity.Role;
import com.example.entity.SysPermission;
import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.exception.ServiceException;
import com.example.mapper.RoleMapper;
import com.example.mapper.UserRoleMapper;
import com.example.service.RoleService;
import com.example.vo.PageRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lay
 * @since 2022-04-17
 */
@Service
public class RoleServiceImp extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;
    @Resource
    private RoleMapper roleMapper;

    @Override
    public IPage<Role> pageRoles(PageRoleVo pageRoleVo) {
        IPage<Role> page = new Page<>(pageRoleVo.getPageNo(), pageRoleVo.getPageSize());
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .like(!ObjectUtil.isEmpty(pageRoleVo.getName()), Role::getName, pageRoleVo.getName());

        return page(page, wrapper);
    }

    @Override
    public Boolean getOneRole(Integer id) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getRoleId,id);
        List<UserRole> roles = userRoleMapper.selectList(wrapper);
        if(roles==null||roles.size()==0){
            return true;
        }else {
            throw new ServiceException(Constants.CODE_600,"该权限下存在用户");
        }
    }

    @Override
    public Result findListPage(Integer pageNum, Integer pageSize, String username) {
        LambdaQueryWrapper<Role> wrapper = Wrappers.<Role>lambdaQuery();
        if (StrUtil.isNotBlank(username)) {
            wrapper.like(Role::getName, username);
        }
        Page<Role> BookPage = roleMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(BookPage);
    }

    @Override
    public Result saveRuleData(Role role) {
        Integer result=0;
        Integer id=role.getId();
        Role newData=roleMapper.selectById(id);
        if(newData!=null){
            //修改
            role.setId(id);
            result=roleMapper.updateById(role);
        }else{
            //新增
            role.setId(null);
            result=roleMapper.insert(role);
        }
        if(result>0){
            return Result.success("保存成功");
        }else{
            return Result.error(-1,"保存失败");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        Integer result=roleMapper.deleteById(id);
        if(result>0){
            return Result.success("删除成功");
        }else{
            return Result.error(-1,"删除失败");
        }
    }
}
