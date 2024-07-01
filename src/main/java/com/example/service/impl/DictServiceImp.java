package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.Constants;
import com.example.entity.Dict;
import com.example.mapper.DictMapper;
import com.example.service.DictService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lay
 * @since 2022-04-18
 */
@Service
public class DictServiceImp extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Override
    public List<Dict> listType() {
        LambdaQueryWrapper<Dict> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Dict::getType, Constants.DICT_ICONS);
       return list(wrapper);
    }
}
