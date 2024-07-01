package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 
 * </p>
 *
 * @author
 * @since 2023-06-29
 */
@Data
@TableName("sys_permission")
@Component
@ApiModel(value = "Permission对象", description = "菜单表")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("父级ID")
    @TableField("pid")
    private Integer pid;

    @ApiModelProperty("功能按钮名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("功能按钮code")
    @TableField("code")
    private String code;

    @ApiModelProperty("路径")
    @TableField("path")
    private String path;

    @ApiModelProperty("comment")
    @TableField("comment")
    private String comment;

    @ApiModelProperty("图标")
    @TableField("icon")
    private String icon;

    @TableField(exist = false)
    private List<SysPermission> children;



}
