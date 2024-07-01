package com.example.mapper;

import com.example.dto.UserDto;
import com.example.entity.UserRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.vo.PageUserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lee
 * @since 2022-06-09
 */
@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {

    List<UserDto> getRoleList(PageUserVo pageUserVo);
}
