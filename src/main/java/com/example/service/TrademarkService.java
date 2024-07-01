package com.example.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.Result;
import com.example.entity.Trademark;


/**
 * <p>
 *  服务类
 * </p>
 */
public interface TrademarkService extends IService<Trademark> {

    /**
     * 查询权限列表
     * @return
     */
    Result findListPage(Integer pageNum, Integer pageSize, String tmName);

    /**
     * 新增、修改品牌
     * @param trademark
     * @return
     */
    Result saveTrademarkData(Trademark trademark);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    Result deleteById(Integer id);
}
