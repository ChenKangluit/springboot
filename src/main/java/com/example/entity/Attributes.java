package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 商品属性表
 * </p>
 *
 * @author Yang
 * @since 2023-06-30
 */
@Data
@Component
@TableName("attributes")
@ApiModel(value = "attributes对象", description = "商品属性表")
public class Attributes implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("属性名称")
    @TableField("attr_name")
    private String attrName;

    @ApiModelProperty("分类ID")
    @TableField("category_id")
    private Integer categoryId;

    @ApiModelProperty("分类级别")
    @TableField("category_level")
    private Integer categoryLevel;

    @ApiModelProperty("属性值数组")
    @TableField(exist = false)
    private List<AttributesValue> attrValueList;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("下拉选名称")
    @TableField(exist = false)
    private String name;

    @ApiModelProperty("下拉选值")
    @TableField(exist = false)
    private Integer value;


}
