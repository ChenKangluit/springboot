package com.example.controller;


import com.example.common.Result;
import com.example.entity.Role;
import com.example.entity.Trademark;
import com.example.service.FileLoadService;
import com.example.service.RoleMenuService;
import com.example.service.RoleService;
import com.example.service.TrademarkService;
import com.example.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <p>
 * 商品品牌表前端控制器
 * </p>
 */
@RestController
@RequestMapping("/admin/product/baseTrademark")
public class TrademarkController {

    @Resource
    private TrademarkService trademarkService;
    @Autowired
    private FileLoadService fileLoadService;

    /**
     * 分页查询
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") String tmName) {

        return trademarkService.findListPage(pageNum, pageSize, tmName);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Trademark trademark) {
        return trademarkService.saveTrademarkData(trademark);
    }

    @PutMapping("/update")
    public Result update(@RequestBody Trademark trademark) {
        return trademarkService.saveTrademarkData(trademark);
    }

    @DeleteMapping("/remove/{id}")
    public Result delete(@PathVariable Integer id) {
        return trademarkService.deleteById(id);
    }

    /***
     * 上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/fileUpload")
    public  Result upload(@RequestParam MultipartFile file) throws IOException {
        String fileUrl=fileLoadService.FileUpload(file);
        return Result.success(fileUrl);
    }


}
