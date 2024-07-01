package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.Result;
import com.example.entity.Attributes;
import com.example.entity.SkuData;
import com.example.entity.SpuData;
import com.example.entity.Trademark;

import java.util.Map;


/**
 * <p>
 *  商品管理服务类
 * </p>
 */
public interface ProductService{

    /**
     * 查询商品分类
     * @return
     */
    Result getCategory(Map map);

    /**
     * 查询商品分类
     * @return
     */
    Result getCategoryByPid(Integer pid);

    /**
     * 查询商品属性列表
     * @return
     */
    Result attrInfoList(Integer category1Id,Integer category2Id,Integer category3Id);

    /**
     * 保存商品属性
     * @return
     */
    Result saveAttrInfo(Attributes attributes);

    /**
     * 删除商品属性
     * @return
     */
    Result deleteAttr(Integer id);

    /**
     * 分页查询商品
     * @return
     */
    Result findPage(Integer pageNum,Integer pageSize,Integer category3Id);

    /**
     * 获取全部的SPU的品牌的数据
     * @return
     */
    Result getTrademarkList();

    /**
     * 获取整个项目全部的销售属性
     * @return
     */
    Result baseSaleAttrList();

    /**
     * 保存spu
     * @return
     */
    Result saveSpuInfo(SpuData spuData);

    /**
     * 获取某一个已有的SPU下全部商品的图片地址
     * @return
     */
    Result spuImageList(Integer spuId);

    /**
     * 获取某一个SPU下全部的已有的销售属性接口地址
     * @return
     */
    Result spuSaleAttrList(Integer spuId);

    /**
     * 保存sku
     * @return
     */
    Result saveSkuInfo(SkuData skuData);


    /**
     * 分页查询商品
     * @return
     */
    Result findSkuPage(Integer pageNum,Integer pageSize);















}
