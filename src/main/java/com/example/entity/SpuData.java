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
 * spu商品信息表
 * </p>
 *
 * @author Yang
 * @since 2023-06-30
 */
@Data
@Component
@TableName("spu_data")
@ApiModel(value = "attributes_value对象", description = "spu商品信息表")
public class SpuData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("收集三级分类的ID")
    @TableField("category3_id")
    private Integer category3Id;

    @ApiModelProperty("品牌的ID")
    @TableField("tm_id")
    private Integer tmId;

    @ApiModelProperty("SPU的名字")
    @TableField("spu_name")
    private String spuName;

    @ApiModelProperty("SPU的描述")
    @TableField("description")
    private String description;


    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("spu图片集合")
    @TableField(exist = false)
    private List<SpuImage> spuImageList;

    @ApiModelProperty("在售商品集合")
    @TableField(exist = false)
    private List<SpuSaleAttr> spuSaleAttrList;




}
