package com.example.controller;

import com.example.common.Result;
import com.example.entity.Attributes;
import com.example.entity.SkuData;
import com.example.entity.SpuData;
import com.example.entity.Trademark;
import com.example.service.FileLoadService;
import com.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *  商品管理前端控制器
 * </p>
 *
 * @author Yang
 * @since 2022-06-30
 */
@RestController
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private FileLoadService fileLoadService;

    @GetMapping("/getCategory1")
    public Result getCategory1(Map map){
        return productService.getCategory(map);
    }

    @GetMapping("/getCategory2/{id}")
    public Result getCategory2(@PathVariable Integer id){
        return productService.getCategoryByPid(id);
    }

    @GetMapping("/getCategory3/{id}")
    public Result getCategory3(@PathVariable Integer id){
        return productService.getCategoryByPid(id);
    }

    @GetMapping("/attrInfoList/{category1Id}/{category2Id}/{category3Id}")
    public Result attrInfoList(@PathVariable Integer category1Id,@PathVariable Integer category2Id,
                               @PathVariable Integer category3Id){
        return productService.attrInfoList(category1Id,category2Id,category3Id);
    }

    @PostMapping("/saveAttrInfo")
    public Result saveAttrInfo(@RequestBody Attributes attributes) {
        return productService.saveAttrInfo(attributes);
    }

    @DeleteMapping("/deleteAttr/{id}")
    public Result deleteAttr(@PathVariable Integer id) {
        return productService.deleteAttr(id);
    }

    /**
     * 分页查询
     */
    @GetMapping("/{pageNum}/{pageSize}")
    public Result findPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize,
                           @RequestParam(defaultValue = "") Integer category3Id) {

        return productService.findPage(pageNum, pageSize, category3Id);
    }

    @GetMapping("/baseTrademark/getTrademarkList")
    public Result getTrademarkList(){
        return productService.getTrademarkList();
    }

    @GetMapping("/spuSaleAttrList")
    public Result spuSaleAttrList(){
        return productService.getTrademarkList();
    }

    @GetMapping("/baseSaleAttrList")
    public Result baseSaleAttrList(){
        return productService.baseSaleAttrList();
    }

    @PostMapping("/saveSpuInfo")
    public Result saveSpuInfo(@RequestBody SpuData spuData) {
        return productService.saveSpuInfo(spuData);
    }

    @GetMapping("/spuImageList/{spuId}")
    public Result spuImageList(@PathVariable Integer spuId){
        return productService.spuImageList(spuId);
    }

    @GetMapping("/spuSaleAttrList/{spuId}")
    public Result spuSaleAttrList(@PathVariable Integer spuId){
        return productService.spuSaleAttrList(spuId);
    }

    @PostMapping("/updateSpuInfo")
    public Result updateSpuInfo(@RequestBody SpuData spuData) {
        return productService.saveSpuInfo(spuData);
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

    @PostMapping("/saveSkuInfo")
    public Result saveSkuInfo(@RequestBody SkuData skuData) {
        return productService.saveSkuInfo(skuData);
    }


    /**
     * 分页查询sku数据
     */
    @GetMapping("/list/{pageNum}/{pageSize}")
    public Result findSkuPage(@RequestParam(defaultValue = "1") Integer pageNum,
                           @RequestParam(defaultValue = "10") Integer pageSize) {

        return productService.findSkuPage(pageNum, pageSize);
    }






}
