package com.example.controller;


import com.example.common.Result;
import com.example.entity.Menu;
import com.example.service.DictService;
import com.example.service.MenuService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lay
 * @since 2022-04-17
 */
@RestController
@RequestMapping("/menus")
public class MenuController {


    @Resource
    private MenuService menuService;
    @Autowired
    private DictService dictService;

    @GetMapping
    public Result MenuList( @Param("name") String name) {
        return Result.success(menuService.findMenus(name));
    }


    @PostMapping
    public Result save(@RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
    }



    @DeleteMapping("/del/{id}")

    public Result delete(@PathVariable("id") Integer id) {
        return Result.success(menuService.removeMenu(id));
    }


    @PostMapping("/list")
    public Result MenuRolelist(){
        return Result.success(menuService.getMenuRole());
    }

    @GetMapping("/icons")
    public Result getIconAll(){
        return Result.success(dictService.listType());
    }


}
