package com.example.entity;

import cn.hutool.core.annotation.Alias;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_user")
@ApiModel(value = "User对象", description = "用户表")
@Component
public class User implements Serializable {

    @ApiModelProperty("id")
    @TableId(type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    @Alias("用户名")
    private String username;

    @ApiModelProperty("用户姓名")
    @Alias("用户姓名")
    private String name;

    @ApiModelProperty("密码")
    @Alias("密码")
    @JsonIgnore
    private String password;

    @Alias("角色名称")
    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;


}
