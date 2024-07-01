package com.example.service;

import com.example.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lay
 * @since 2022-04-17
 */
public interface MenuService extends IService<Menu> {

    /**
     * 二级菜单
     * @param name
     * @return
     */
    List<Menu> findMenus(String name);

    /**
     * 删除菜单
     * @param id
     * @return
     */
    Boolean removeMenu(Integer id);

    /**
     * 查询权限对应菜单
     * @return
     */
    List<Menu> getMenuRole();
}
