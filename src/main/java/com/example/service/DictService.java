package com.example.service;

import com.example.entity.Dict;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lay
 * @since 2022-04-18
 */
public interface DictService extends IService<Dict> {

    /**
     * ico 图标
     * @return
     */
    List<Dict> listType();
}
