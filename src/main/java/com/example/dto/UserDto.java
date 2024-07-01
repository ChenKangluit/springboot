package com.example.dto;


import com.example.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {

private Integer id;
    private List<Role> roleList;

    private String username;


    private String nickname;

    private String email;

    private String phone;

    private String address;

    private String avatarUrl;

}
