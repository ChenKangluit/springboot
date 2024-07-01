package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.entity.UserRole;
import com.example.mapper.UserRoleMapper;
import com.example.service.UserRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.vo.UserRoleVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lee
 * @since 2022-06-09
 */
@Service
public class UserRoleServiceImp extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Override
    public Boolean UserRole(UserRoleVo userRoleVo) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,userRoleVo.getUserId());
        remove(wrapper);
        for (Integer integer : userRoleVo.getRoleIdList()) {
            UserRole userRole = UserRole.builder()
                    .userId(userRoleVo.getUserId())
                    .roleId(integer).build();
            save(userRole);
        }
        return true;
    }
}
