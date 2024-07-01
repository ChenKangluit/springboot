package com.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Select("select menu_id from sys_role_menu where role_id = #{roleId}")
    List<Integer> selectByRoleId(Integer roleId);

    List<RoleMenu> selectBatchByRoleIds(List<Integer> collect1);

}
