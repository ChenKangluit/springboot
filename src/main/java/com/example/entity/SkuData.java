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
@TableName("sku_data")
@ApiModel(value = "attributes_value对象", description = "spu商品信息表")
public class SkuData implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("收集三级分类的ID")
    @TableField("category3_id")
    private Integer category3Id;

    @ApiModelProperty("spuId")
    @TableField("spu_id")
    private Integer spuId;

    @ApiModelProperty("品牌的ID")
    @TableField("tm_id")
    private Integer tmId;

    @ApiModelProperty("SKU的名字")
    @TableField("sku_name")
    private String skuName;

    @ApiModelProperty("sku价格")
    @TableField("price")
    private String price;

    @ApiModelProperty("sku重量")
    @TableField("weight")
    private String weight;

    @ApiModelProperty("sku的描述")
    @TableField("sku_desc")
    private String skuDesc;

    @ApiModelProperty("sku图片地址")
    @TableField("sku_default_img")
    private String skuDefaultImg;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @ApiModelProperty("spu图片集合")
    @TableField(exist = false)
    private List<SpuImage> skuAttrValueList;

    @ApiModelProperty("在售商品集合")
    @TableField(exist = false)
    private List<SpuSaleAttr> skuSaleAttrValueList;




}
