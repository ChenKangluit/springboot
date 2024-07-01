package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author lay
 * @since 2022-04-18
 */

@Data
@TableName("sys_dict")
@Component
@ApiModel(value = "Dict对象", description = "图标库")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("内容")
    @TableField("value")
    private String value;

    @ApiModelProperty("类型")
    @TableField("type")
    private String type;


}
