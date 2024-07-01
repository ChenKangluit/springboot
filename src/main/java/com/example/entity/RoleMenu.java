package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@TableName("sys_role_menu")
@Data
@Component
@ApiModel(value = "RoleMenU对象",description = "权限对应菜单")
public class RoleMenu implements Serializable {

    @TableField("role_id")
    @ApiModelProperty("权限id")
    private Integer roleId;

    @TableField("menu_id")
    @ApiModelProperty("菜单id")
    private Integer menuId;

}
