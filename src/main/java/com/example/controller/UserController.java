package com.example.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.common.Result;
import com.example.common.RoleMenu;
import com.example.entity.User;
import com.example.entity.UserRole;
import com.example.service.IUserService;
import com.example.service.UserRoleService;
import com.example.utils.TokenUtils;
import com.example.vo.PageUserVo;
import com.example.vo.UserVo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {


    @Resource
    private IUserService userService;

    @Resource
    private UserRoleService userRoleService;

    @GetMapping
    public Result UserList() {
        return Result.success(userService.list());
    }

    @PostMapping("/count")
    public Result UserCount(){
        return Result.success(userService.count());
    }

    /**
     * 查询一个用户
     * @param userVo
     * @return
     */
    @PostMapping("/one")
    public Result getUserOne(@RequestBody UserVo userVo) {
        return Result.success(userService.getUserOne(userVo));
    }

    /**
     * 查询一个用户
     * @param userVo
     * @return
     */
    @GetMapping("/userInfo")
    public Result userInfo(UserVo userVo) {
        Map map=new HashMap();
        map.put("avatar","https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        map.put("name","admin");
        String[] roles=new String[1];
        roles[0]="前端开发工程师";
        map.put("roles",roles);

        String[] routes=new String[32];
        routes[0]="44";
        routes[1]="User";
        routes[2]="Category";
        routes[3]="Discount";
        routes[4]="ActivityEdit";
        routes[5]="CouponRule";
        routes[6]="Product";
        routes[7]="Activity";
        routes[8]="Acl";
        routes[9]="Permission";
        routes[10]="Role";
        routes[11]="Product";
        routes[12]="Trademark";
        routes[13]="Spu";
        routes[14]="Sku";
        routes[15]="Attr";



        map.put("routes",routes);

        String[] buttons=new String[32];
        buttons[0]="cuser.detail";
        buttons[1]="cuser.update";
        buttons[2]="cuser.delete";
        buttons[3]="btn.User.add";
        buttons[4]="btn.User.remov";
        buttons[5]="btn.User.update";
        buttons[6]="btn.User.assgin";
        buttons[7]="btn.Permission.add";
        buttons[8]="btn.Permission.update";
        buttons[9]="btn.Permission.remove";
        buttons[10]="btn.Role.assgin";
        buttons[11]="btn.Role.add";
        buttons[12]="btn.Role.update";

        buttons[13]="btn.Trademark.add";
        buttons[14]="btn.Trademark.update";
        buttons[15]="btn.Trademark.remove";

        buttons[16]="btn.Attr.add";
        buttons[17]="btn.Attr.update";
        buttons[18]="btn.Attr.remove";


        map.put("buttons",buttons);

        return Result.success(map);
    }



    /**
     * 添加用户
     * @param user
     * @return
     */
    @PostMapping
    public Result save(@RequestBody User user) {
        return Result.success(userService.saveOrUpdate(user));
    }

    /**
     * 修改密码
     * @param password
     * @return
     */
    @PutMapping("/pwd")
    public Result updatePwd(@RequestBody String password) {
        return Result.success(userService.editUser(password));
    }

    /**
     * 注销
     * @return
     */
    @PostMapping("/logout")
    public Result logout(@RequestHeader("token") String token){
        return Result.success(userService.logout(token));
    }

    /**
     * 注册
     * @param userVo
     * @return
     */
    @PostMapping("/register")
    public Result Register(@RequestBody UserVo userVo) {
        return Result.success(userService.Reqister(userVo));
    }

    /**
     * 登录
     * @param userVo
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody UserVo userVo) {
        return Result.success(userService.login(userVo));
    }

    /**
     * 登录
     * @param userVo
     * @return
     */
    @PostMapping("/reLogin")
    public Result reLogin(@RequestBody UserVo userVo) {
        return Result.success(userService.login(userVo).get("token"));
    }


    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")

    public Result delete(@PathVariable("id") Integer id) {
        LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserRole::getUserId,id);
        userRoleService.remove(wrapper);
        userService.removeById(id);
        return Result.success();
    }

    /**
     * 批量删除
     * @param ids
     * @return
     */
    @PostMapping("/del/batch")

    public Result deleteBatch(@RequestBody List<Integer> ids) {
        for (Integer id : ids) {
            LambdaQueryWrapper<UserRole> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(UserRole::getUserId,id);
            userRoleService.remove(wrapper);
        }
        return Result.success(userService.removeBatchByIds(ids));
    }

    /**
     * 导出
     */
    @GetMapping("/export")

    public void export(HttpServletResponse response){
        userService.export(response);
    }

    /**
     * 导入
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/import")

    public Result imp(MultipartFile file)  {
        return Result.success(userService.importUsers(file));
    }


    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result findPage(PageUserVo pageUserVo) {
        return Result.success( userService.pageUser(pageUserVo));
    }

    @PostMapping("/current")
    public Result getCurrentUser(){
        return Result.success(TokenUtils.getLoginUser());
    }
}
