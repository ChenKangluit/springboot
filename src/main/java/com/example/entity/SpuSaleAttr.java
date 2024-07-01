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

/**
 * <p>
 * 已有的销售属性值表
 * </p>
 */
@Data
@TableName("spu_sale_attr")
@Component
@ApiModel(value = "spu_sale_attr对象", description = "已有的销售属性值表")
public class SpuSaleAttr implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("SPU的ID")
    @TableField("spu_id")
    private Integer spuId;

    @ApiModelProperty("已有的属性的ID")
    @TableField("base_sale_attr_id")
    private Integer baseSaleAttrId;

    @ApiModelProperty("已有的属性的名称")
    @TableField("sale_attr_name")
    private String saleAttrName;

    @ApiModelProperty("是否选中")
    @TableField("is_checked")
    private boolean isChecked;

    @ApiModelProperty("图片名称")
    @TableField("sale_attr_value_name")
    private String saleAttrValueName;

    @ApiModelProperty("创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @ApiModelProperty("修改时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

}
