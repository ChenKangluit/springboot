package com.example.entity;

import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Component
public class LoginUser implements UserDetails, Serializable {

    private User user;

    private List<String> roleList;

    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> authorities;


    public LoginUser(User user, List<String> roleList) {
        this.user = user;
        this.roleList = roleList;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (ObjectUtil.isNotEmpty(authorities)){
            return authorities;
        }
         authorities = roleList.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return authorities;
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
