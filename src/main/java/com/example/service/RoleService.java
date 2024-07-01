package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.Result;
import com.example.entity.Role;
import com.example.entity.SysPermission;
import com.example.vo.PageRoleVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lay
 * @since 2022-04-17
 */
public interface RoleService extends IService<Role> {

    /**
     * 分页查询权限表
     * @param pageRoleVo
     * @return
     */
    IPage<Role> pageRoles(PageRoleVo pageRoleVo);

    Boolean getOneRole(Integer id);

    /**
     * 查询权限列表
     * @return
     */
    Result findListPage(Integer pageNum, Integer pageSize, String username);

    /**
     * 新增、修改角色
     * @param sysPermission
     * @return
     */
    Result saveRuleData(Role role);

    /**
     * 删除角色
     * @param id
     * @return
     */
    Result deleteById(Integer id);
}
