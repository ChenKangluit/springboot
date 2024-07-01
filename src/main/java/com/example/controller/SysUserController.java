package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.Result;
import com.example.entity.SysPermission;
import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.service.IUserService;
import com.example.service.UserRoleService;
import com.example.utils.TokenUtils;
import com.example.vo.PageUserVo;
import com.example.vo.UserVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/acl/user")
public class SysUserController {


    @Resource
    private IUserService userService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result findPage(PageUserVo pageUserVo) {
        return Result.success( userService.pageUser(pageUserVo));
    }

    @PostMapping("/current")
    public Result getCurrentUser(){
        return Result.success(TokenUtils.getLoginUser());
    }

    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                              @RequestParam(defaultValue = "10") Integer pageSize,
                              @RequestParam(defaultValue = "") String username) {

        return userService.findListPage(pageNum,pageSize,username);
    }

    @PostMapping("/save")
    public Result save(@RequestBody User user) {
        return userService.saveUserData(user);
    }

    @PutMapping("/update")
    public Result update(@RequestBody User user) {
        return userService.saveUserData(user);
    }

    @DeleteMapping("/remove/{id}")
    public Result delete(@PathVariable Integer id) {
        return userService.deleteById(id);
    }
}
