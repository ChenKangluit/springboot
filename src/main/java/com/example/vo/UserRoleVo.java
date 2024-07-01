package com.example.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserRoleVo {
    private Integer userId;
    private List<Integer> roleIdList;
}
