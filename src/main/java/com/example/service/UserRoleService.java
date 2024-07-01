package com.example.service;

import com.example.entity.UserRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.vo.UserRoleVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lee
 * @since 2022-06-09
 */
public interface UserRoleService extends IService<UserRole> {

    Boolean UserRole(UserRoleVo userRoleVo);
}
