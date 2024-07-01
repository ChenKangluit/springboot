package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 
 * </p>
 *
 * @author lay
 * @since 2022-04-17
 */
@Data
@TableName("sys_menu")
@Component
@ApiModel(value = "Menu对象", description = "菜单表")
public class Menu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜单名")
    @TableField("name")
    private String name;

    @ApiModelProperty("url")
    @TableField("path")
    private String path;

    @ApiModelProperty("图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("描述")
    @TableField("description")
    private String description;

    @ApiModelProperty("父级id")
    @TableField("pid")
    private Integer pid;

    @TableField(exist = false)
    private List<Menu> children;

    @ApiModelProperty("页面路径")
    @TableField("view_path")
    private String viewPath;

}
