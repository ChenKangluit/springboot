package com.example.controller;


import com.example.common.Result;
import com.example.entity.Role;
import com.example.service.RoleMenuService;
import com.example.service.RoleService;
import com.example.vo.PageRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lay
 * @since 2022-04-17
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @GetMapping
    public Result UserList() {
        return Result.success(roleService.list());
    }

    @PostMapping("/one/{id}")
    public Result getRoleOnes(@PathVariable Integer id){
        return Result.success(roleService.getOneRole(id));
    }



    @PostMapping
    public Result save(@RequestBody Role role) {
        return Result.success(roleService.saveOrUpdate(role));
    }



    @DeleteMapping("/del/{id}")

    public Result delete(@PathVariable("id") Integer id) {
        return Result.success(roleService.removeById(id));
    }

    @PostMapping("/del/batch")

    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(roleService.removeBatchByIds(ids));
    }

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result findPage(PageRoleVo pageRoleVo) {
        return Result.success(roleService.pageRoles(pageRoleVo));
    }

    @PostMapping("/rolemenu/{roleId}")
    public Result saveRoleMenu(@PathVariable Integer roleId ,@RequestBody List<Integer> menuIds) {
        return Result.success(roleMenuService.setRoleMenu(roleId,menuIds));
    }

    @GetMapping("/rolemenu/{roleId}")
    public Result getRoleMenu(@PathVariable Integer roleId ) {
        return Result.success( roleMenuService.getRoleMenu(roleId));
    }

}
