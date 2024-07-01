package com.example;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.Role;
import com.example.mapper.RoleMapper;
import com.example.service.RoleService;
import com.example.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GeneralTemplateApplicationTests {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleMapper roleMapper;

    @Test
    void contextLoads() {

    }

    @Test
    public void test() {
        Result page = userServiceImpl.findListPage(1, 5, "12345");
        System.out.println(page);
    }

    @Test
    public void test1() {
        Result test = roleService.findListPage(1, 5, "管理员");
        System.out.println(test);
    }
}
