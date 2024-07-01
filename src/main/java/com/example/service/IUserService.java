package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.common.Result;
import com.example.entity.SysPermission;
import com.example.entity.User;
import com.example.vo.PageUserVo;
import com.example.vo.UserVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public interface IUserService extends IService<User> {

    /**
     * 用户登录
     * @param userDto
     * @return
     */
    Map<String,Object> login(UserVo userDto);

    /**
     * 用户注册
     * @param userVo
     * @return
     */
    User Reqister(UserVo userVo);

    /**
     * 获取一个用户
     * @param userVo
     * @return
     */
    User getUserOne(UserVo userVo);

    /**
     * 修改用户密码
     * @param pwdVo
     * @return
     */
    Boolean editUser(String pwdVo);

    /**
     * 分页查询 用户
     * @param pageUserVo
     * @return
     */
    Map<String, Object> pageUser(PageUserVo pageUserVo);

    /**
     * 批量导出数据
     * @param response
     */
    void export(HttpServletResponse response);

    /**
     * 批量导入用户数据
     * @param file
     * @return
     */
    Boolean importUsers(MultipartFile file);

    /**
     * 用户注销
     * @return
     */
    Boolean logout(String token);

    /**
     * 查询用户列表
     * @return
     */
    Result findListPage(Integer pageNum,Integer pageSize,String username);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    Result saveUserData(User user);

    /**
     * 删除用户信息
     * @param id
     * @return
     */
    Result deleteById(Integer id);




}
