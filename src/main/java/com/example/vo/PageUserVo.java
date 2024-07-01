package com.example.vo;

import lombok.Data;

@Data
public class PageUserVo {
    /**
     * 分页查询
     */
    private Integer pageNo;
    private Integer pageSize;
    private String usernameSearch;
}
