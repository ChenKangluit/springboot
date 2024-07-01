package com.example.mapper;

import com.example.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lay
 * @since 2022-04-17
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {


}
