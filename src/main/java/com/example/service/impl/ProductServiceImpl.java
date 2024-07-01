package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.common.Result;
import com.example.entity.*;
import com.example.mapper.*;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private AttributesMapper attributesMapper;
    @Autowired
    private AttributesValueMapper attributesValueMapper;
    @Autowired
    private SpuDataMapper spuDataMapper;
    @Autowired
    private TrademarkMapper trademarkMapper;
    @Autowired
    private SpuImageMapper spuImageMapper;
    @Autowired
    private SpuSaleAttrMapper spuSaleAttrMapper;
    @Autowired
    private SkuDataMapper skuDataMapper;



    @Override
    public Result getCategory(Map map) {
        QueryWrapper<Category>  queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pid",0);
        List<Category> categoryList=categoryMapper.selectList(queryWrapper);
        return Result.success(categoryList);
    }

    @Override
    public Result getCategoryByPid(Integer pid) {
        QueryWrapper<Category>  queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("pid",pid);
        List<Category> categoryList=categoryMapper.selectList(queryWrapper);
        return Result.success(categoryList);
    }

    @Override
    public Result attrInfoList(Integer category1Id, Integer category2Id, Integer category3Id) {
        QueryWrapper<Attributes>  queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("category_id",category3Id);
        List<Attributes> attributesList=attributesMapper.selectList(queryWrapper);
        for (Attributes attributes : attributesList) {
            Integer id=attributes.getId();
            QueryWrapper<AttributesValue>  queryWrapper1=new QueryWrapper<>();
            queryWrapper1.eq("attr_id",id);
            List<AttributesValue> attrValueList=attributesValueMapper.selectList(queryWrapper1);
            attributes.setAttrValueList(attrValueList);
        }
        return Result.success(attributesList);
    }

    @Override
    public Result saveAttrInfo(Attributes attributes) {
        Integer result=0;
        if(ObjectUtil.isEmpty(attributes.getId())){
            //新增
            result=attributesMapper.insert(attributes);
        }else{
            //修改
            result=attributesMapper.updateById(attributes);
        }
        if(result>0){
            if(!ObjectUtil.isEmpty(attributes.getAttrValueList())){
                List<AttributesValue> valueList=attributes.getAttrValueList();
                System.out.println(valueList);
                for (AttributesValue attributesValue : valueList) {
                    attributesValue.setAttrId(attributes.getId());
                    if(ObjectUtil.isEmpty(attributesValue.getId())){
                        attributesValueMapper.insert(attributesValue);
                    }else{
                        attributesValueMapper.updateById(attributesValue);
                    }
                }
            }
            return Result.success("保存成功");
        }else{
            return Result.error(-1,"保存失败");
        }
    }

    @Override
    public Result deleteAttr(Integer id) {
        Attributes attributes=attributesMapper.selectById(id);
        //先删除主表数据
        Integer result=0;
        result=attributesMapper.deleteById(id);
        //再删除从表数据
        Integer attrId=attributes.getId();
        QueryWrapper<AttributesValue> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("attr_id",attrId);
        List<AttributesValue> valueList=attributesValueMapper.selectList(queryWrapper);
        for (AttributesValue attributesValue : valueList) {
            attributesValueMapper.deleteById(attributesValue);
        }

        if(result>0){
            return Result.success("删除成功");
        }else{
            return Result.error(-1,"删除失败");
        }
    }

    @Override
    public Result findPage(Integer pageNum, Integer pageSize, Integer category3Id) {
        LambdaQueryWrapper<SpuData> wrapper = Wrappers.<SpuData>lambdaQuery();
        if (category3Id!=null) {
            wrapper.like(SpuData::getCategory3Id, category3Id);
        }
        Page<SpuData> spuDataPage = spuDataMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(spuDataPage);
    }

    @Override
    public Result getTrademarkList() {
        List<Trademark> trademarkList=trademarkMapper.selectList(new QueryWrapper<>());
        return Result.success(trademarkList);
    }

    @Override
    public Result baseSaleAttrList() {
        List<Attributes> attributesList=attributesMapper.selectList(new QueryWrapper<>());
        for (Attributes attributes : attributesList) {
            attributes.setName(attributes.getAttrName());
            attributes.setValue(attributes.getId());
        }
        return Result.success(attributesList);
    }

    @Override
    public Result saveSpuInfo(SpuData spuData) {
        Integer result=0;
        if(ObjectUtil.isEmpty(spuData.getId())){
            //新增
            result=spuDataMapper.insert(spuData);
        }else{
            //修改
            result=spuDataMapper.updateById(spuData);
        }

        //System.out.println("操作之后的实体信息：："+spuData);
        Integer spuId=spuData.getId();
        if(result>0){
            if(!ObjectUtil.isEmpty(spuData.getSpuImageList())){
                List<SpuImage> imageList=spuData.getSpuImageList();
                for (SpuImage spuImage : imageList) {
                    spuImage.setSpuId(spuId);
                    String imgUrl=spuImage.getImgUrl();
                    imgUrl=imgUrl.replaceAll("blob:","");
                    spuImage.setImgUrl(imgUrl);
                    if(ObjectUtil.isEmpty(spuImage.getId())){
                        spuImageMapper.insert(spuImage);
                    }else{
                        spuImageMapper.updateById(spuImage);
                    }
                }
            }
            if(!ObjectUtil.isEmpty(spuData.getSpuSaleAttrList())){
                List<SpuSaleAttr> spuSaleAttrList=spuData.getSpuSaleAttrList();
                for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                    spuSaleAttr.setSpuId(spuId);
                    if(ObjectUtil.isEmpty(spuSaleAttr.getId())){
                        spuSaleAttrMapper.insert(spuSaleAttr);
                    }else{
                        spuSaleAttrMapper.updateById(spuSaleAttr);
                    }
                }

            }
            return Result.success("保存成功");
        }else{
            return Result.error(-1,"保存失败");
        }
    }

    @Override
    public Result spuImageList(Integer spuId) {
        QueryWrapper<SpuImage> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("spu_id",spuId);
        List<SpuImage> spuImageList=spuImageMapper.selectList(queryWrapper);

        return Result.success(spuImageList);
    }

    @Override
    public Result spuSaleAttrList(Integer spuId) {
        QueryWrapper<SpuSaleAttr> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("spu_id",spuId);
        List<SpuSaleAttr> spuSaleAttrList=spuSaleAttrMapper.selectList(queryWrapper);

        return Result.success(spuSaleAttrList);
    }

    @Override
    public Result saveSkuInfo(SkuData skuData) {
        Integer result=0;
        if(ObjectUtil.isEmpty(skuData.getId())){
            //新增
            result=skuDataMapper.insert(skuData);
        }else{
            //修改
            result=skuDataMapper.updateById(skuData);
        }

        //System.out.println("操作之后的实体信息：："+spuData);
        Integer spuId=skuData.getId();
        if(result>0){
            /*if(!ObjectUtil.isEmpty(skuData.getSpuImageList())){
                List<SpuImage> imageList=skuData.getSpuImageList();
                for (SpuImage spuImage : imageList) {
                    spuImage.setSpuId(spuId);
                    String imgUrl=spuImage.getImgUrl();
                    imgUrl=imgUrl.replaceAll("blob:","");
                    spuImage.setImgUrl(imgUrl);
                    if(ObjectUtil.isEmpty(spuImage.getId())){
                        spuImageMapper.insert(spuImage);
                    }else{
                        spuImageMapper.updateById(spuImage);
                    }
                }
            }
            if(!ObjectUtil.isEmpty(spuData.getSpuSaleAttrList())){
                List<SpuSaleAttr> spuSaleAttrList=spuData.getSpuSaleAttrList();
                for (SpuSaleAttr spuSaleAttr : spuSaleAttrList) {
                    spuSaleAttr.setSpuId(spuId);
                    if(ObjectUtil.isEmpty(spuSaleAttr.getId())){
                        spuSaleAttrMapper.insert(spuSaleAttr);
                    }else{
                        spuSaleAttrMapper.updateById(spuSaleAttr);
                    }
                }

            }*/
            return Result.success("保存成功");
        }else{
            return Result.error(-1,"保存失败");
        }
    }

    @Override
    public Result findSkuPage(Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<SkuData> wrapper = Wrappers.<SkuData>lambdaQuery();

        Page<SkuData> spuDataPage = skuDataMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(spuDataPage);
    }
}
