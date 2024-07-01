package com.example.controller;


import com.example.common.Result;
import com.example.entity.Menu;
import com.example.entity.SysPermission;
import com.example.service.DictService;
import com.example.service.MenuService;
import com.example.service.SysPermissionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  菜单权限前端控制器
 * </p>
 */
@RestController
@RequestMapping("/admin/acl/permission")
public class SysPermissionController {


    @Resource
    private SysPermissionService sysPermissionService;
    @Autowired
    private DictService dictService;

//    @GetMapping
//    public Result MenuList( @Param("name") String name) {
//        return Result.success(menuService.findMenus(name));
//    }
//
//
//    @PostMapping
//    public Result save(@RequestBody Menu menu) {
//        return Result.success(sysPermissionService.saveOrUpdate(menu));
//    }

    @PostMapping("/save")
    public Result save(@RequestBody SysPermission sysPermission) {
        return sysPermissionService.saveData(sysPermission);
    }

    @PutMapping("/update")
    public Result update(@RequestBody SysPermission sysPermission) {
        return sysPermissionService.saveData(sysPermission);
    }

    @DeleteMapping("/remove/{id}")
    public Result delete(@PathVariable Integer id) {
        return sysPermissionService.deleteById(id);
    }

//
//
//    @DeleteMapping("/del/{id}")
//
//    public Result delete(@PathVariable("id") Integer id) {
//        return Result.success(menuService.removeMenu(id));
//    }


    @GetMapping("/list")
    public Result getPermissionList(Integer pid){
        return Result.success(sysPermissionService.getPermissionList(pid));
    }

    @GetMapping("/icons")
    public Result getIconAll(){
        return Result.success(dictService.listType());
    }



}
