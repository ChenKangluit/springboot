package com.example.controller;

import com.example.common.Result;
import com.example.entity.UserRole;
import com.example.service.UserRoleService;
import com.example.vo.UserRoleVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    @PostMapping
    public Result save(@RequestBody UserRoleVo userRoleVo){
        return Result.success(userRoleService.UserRole(userRoleVo));
    }
}
