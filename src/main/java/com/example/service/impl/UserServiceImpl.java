package com.example.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Constants;
import com.example.common.Result;
import com.example.dto.UserDto;
import com.example.entity.*;
import com.example.exception.ServiceException;
import com.example.mapper.RoleMapper;
import com.example.mapper.RoleMenuMapper;
import com.example.mapper.UserMapper;
import com.example.mapper.UserRoleMapper;
import com.example.service.IUserService;
import com.example.service.MenuService;
import com.example.utils.RedisUtil;
import com.example.utils.TokenUtils;
import com.example.vo.PageUserVo;
import com.example.vo.UserVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService, UserDetailsService {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserMapper userMapper;


    @Override
    public Boolean logout(String token) {
        //SecurityContextHolder 获取信息
//        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
//        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
//        //redis 删除缓存
//        redisUtil.del(loginUser.getUser().getId().toString());
//        redisUtil.del(token);
        return true;
    }




    @Override
    public Map<String, Object> login(UserVo userVo) {
        String username = userVo.getUsername();
        String pwd = userVo.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(pwd)){
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }

        // 认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userVo.getUsername(),userVo.getPassword());
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

            if(Objects.isNull(authenticate)){
                throw new ServiceException(Constants.CODE_600, "用户名或密码错误");
            }
        //转换类型获取对象
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        //生成token
        String userid = loginUser.getUser().getId().toString();
        String token = TokenUtils.getToken(userid);

        //id存入redis
        redisUtil.set(token,userid,1000*60*60*2);
        redisUtil.set(userid,loginUser,1000*60*60*2);

        //返回数据
        Map<String, Object> map = new HashMap<>();
        map.put("token",token);



        return map;
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(User::getUsername,username);
        User user = getOne(wrapper);

        if(ObjectUtil.isAllEmpty(user)){
            throw new ServiceException(Constants.CODE_600,"用户名或密码错误");
        }

        //TODO 对应权限
        LambdaQueryWrapper<UserRole> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(UserRole::getUserId,user.getId());
        //或的权限id
        List<UserRole> roleList = userRoleMapper.selectList(queryWrapper);

        List<Integer> collect = roleList.stream().map(UserRole::getRoleId).collect(Collectors.toList());

        List<Role> roles = roleMapper.selectBatchIds(collect);


        List<String> list = roles.stream().map(Role::getName).collect(Collectors.toList());


        return new LoginUser(user,list);
    }


    @Override
    public User Reqister(UserVo userVo) {
        String username = userVo.getUsername();
        String password = userVo.getPassword();
        if (StrUtil.isBlank(username) || StrUtil.isBlank(password)) {
            throw new ServiceException(Constants.CODE_400, "参数错误");
        }

        User one = getuserinfo(userVo);
        if(one == null){
            User user = new User();
            userVo.setPassword(new BCryptPasswordEncoder().encode(userVo.getPassword()));
            BeanUtils.copyProperties(userVo, user);
            save(user);
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getUsername,username);
            User one1 = getOne(wrapper);
            UserRole build = UserRole.builder()
                    .userId(one1.getId())
                    .roleId(Constants.ReqisterID).build();
            userRoleMapper.insert(build);
        }else {
            throw new ServiceException(Constants.CODE_600, "用户已存在");
        }
        return one;
    }

    @Override
    public User getUserOne(UserVo userDto) {


        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper
                .eq(User::getUsername,userDto.getUsername());
        User one = getOne(wrapper);
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        boolean flag = bCryptPasswordEncoder.matches(userDto.getPassword(), one.getPassword());

        if (one!=null && flag)
            return one;
        else
            throw new ServiceException(Constants.CODE_600, "没有此用户");
    }

    @Override
    public Boolean editUser(String password) {
        LambdaUpdateWrapper<User> wrapper = new LambdaUpdateWrapper<>();
        //SecurityContextHolder 获取信息
        UsernamePasswordAuthenticationToken authentication = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //解析token

        wrapper
                .eq(User::getId,loginUser.getUser().getId())
                .set(User::getPassword,password);
        return update(null, wrapper);
    }


    @Override
    public Map<String, Object> pageUser(PageUserVo pageUserVo) {
        pageUserVo.setPageNo(pageUserVo.getPageNo()-1);
        List<UserDto> roleList = userRoleMapper.getRoleList(pageUserVo);
        long count = count();
        HashMap<String, Object> map = new HashMap<>();
        map.put("data",roleList);
        map.put("total",count);
        return map;
    }

    @Override
    public void export(HttpServletResponse response) {
        List<User> userList = list();
//        获取 excel 写出流
        try {
            ExcelWriter writer = ExcelUtil.getWriter(true);
            writer.write(userList);
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
            String fileName = URLEncoder.encode("用户信息", "UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xlsx");

            ServletOutputStream out = response.getOutputStream();
            writer.flush(out, true);
            out.close();
            writer.close();
        }catch (IOException e){
            throw new ServiceException(Constants.CODE_500,"导出失败");
        }

    }

    @Override
    public Boolean importUsers(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();

            ExcelReader reader = ExcelUtil.getReader(inputStream);
            List<User> list = reader.readAll(User.class);
            saveBatch(list);
        }catch (IOException e) {
            throw new ServiceException(Constants.CODE_500,"导入失败");
        }
        return true;
    }


    private  User getuserinfo(UserVo userVo){
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(User::getUsername, userVo.getUsername());
        User one;
        try {
            one = getOne(wrapper);
        } catch (Exception e) {
            throw new ServiceException(Constants.CODE_500, "系统错误");
        }
        return one;
    }


    @Override
    public Result findListPage(Integer pageNum, Integer pageSize, String username) {
        LambdaQueryWrapper<User> wrapper = Wrappers.<User>lambdaQuery();
        if (StrUtil.isNotBlank(username)) {
            wrapper.like(User::getUsername, username);
        }
        Page<User> BookPage = userMapper.selectPage(new Page<>(pageNum, pageSize), wrapper);
        return Result.success(BookPage);
    }

    @Override
    public Result saveUserData(User user) {
        Integer result=0;
        Integer id=user.getId();
        User newData=userMapper.selectById(id);
        if(newData!=null){
            //修改
            user.setId(id);
            result=userMapper.updateById(user);
        }else{
            //新增
            user.setId(null);
            result=userMapper.insert(user);
        }
        if(result>0){
            return Result.success("保存成功");
        }else{
            return Result.error(-1,"保存失败");
        }
    }

    @Override
    public Result deleteById(Integer id) {
        Integer result=userMapper.deleteById(id);
        if(result>0){
            return Result.success("删除成功");
        }else{
            return Result.error(-1,"删除失败");
        }
    }


}
