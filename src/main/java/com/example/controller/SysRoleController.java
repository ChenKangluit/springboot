package com.example.controller;


import com.example.common.Result;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.service.RoleMenuService;
import com.example.service.RoleService;
import com.example.utils.TokenUtils;
import com.example.vo.PageRoleVo;
import com.example.vo.PageUserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author lay
 * @since 2022-04-17
 */
@RestController
@RequestMapping("/admin/acl/role")
public class SysRoleController {

    @Resource
    private RoleService roleService;

    @Autowired
    private RoleMenuService roleMenuService;

    @PostMapping("/current")
    public Result getCurrentUser() {
        return Result.success(TokenUtils.getLoginUser());
    }

    /**
     * 分页查询
     */
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String username) {

        return roleService.findListPage(pageNum, pageSize, username);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Role role) {
        return roleService.saveRuleData(role);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Role role) {
        return roleService.saveRuleData(role);
    }

    @DeleteMapping("/remove/{id}")
    public Result delete(@PathVariable Integer id) {
        return roleService.deleteById(id);
    }


}
