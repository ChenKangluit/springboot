package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.entity.Trademark;

import com.example.exception.ServiceException;
import com.example.mapper.TrademarkMapper;

import com.example.service.TrademarkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lay
 * @since 2022-04-17
 */
@Service
public class TrademarkServiceImp extends ServiceImpl<TrademarkMapper, Trademark> implements TrademarkService {


    @Resource
    private TrademarkMapper trademarkMapper;


    @Override
    public Result findListPage(Integer pageNum, Integer pageSize, String tmName) {
        LambdaQueryWrapper<Trademark> wrapper = Wrappers.<Trademark>lambdaQuery();
        if (StrUtil.isNotBlank(tmName)) {
            wrapper.like(Trademark::getTmName, tmName);
        }
        Page<Trademark> BookPage = trademarkMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(BookPage);
    }

    @Override
    public Result saveTrademarkData(Trademark trademark) {
        Integer result=0;
        Integer id=trademark.getId();
        Trademark newData=trademarkMapper.selectById(id);
        if(newData!=null){
            //修改
            trademark.setId(id);
            result=trademarkMapper.updateById(trademark);
        }else{
            //新增
            trademark.setId(null);
            result=trademarkMapper.insert(trademark);
        }
        if(result>0){
            return Result.success("保存成功");
        }else{
            return Result.error(-1,"保存失败");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        Integer result=trademarkMapper.deleteById(id);
        if(result>0){
            return Result.success("删除成功");
        }else{
            return Result.error(-1,"删除失败");
        }
    }
}
