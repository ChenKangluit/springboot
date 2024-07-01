package com.example.vo;

import lombok.Data;

@Data
public class PageFilesVo {
    /**
     * 文件分页
     */
    private Integer pageNo;
    private Integer pageSize;
    private String name;
}
