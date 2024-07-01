package com.example.controller;

import com.example.common.Result;
import com.example.entity.FileLoad;
import com.example.service.FileLoadService;
import com.example.vo.PageFilesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/***
 * 文件上传相关接口
 */
@RestController
@RequestMapping("/file")
public class FileController {


    @Autowired
    private FileLoadService fileLoadService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result findPage(PageFilesVo pageFileVo) {
        return Result.success(fileLoadService.pageFile(pageFileVo));
    }

    @PostMapping("/enable")

    public Result update(@RequestBody FileLoad fileLoad) {
        return Result.success(fileLoadService.isEnable(fileLoad));
    }


    @DeleteMapping("/del/{id}")

    public Result delete(@PathVariable("id") Integer id) {
        return Result.success( fileLoadService.removeById(id));
    }

    @PostMapping("/del/batch")

    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(fileLoadService.removeBatchByIds(ids));
    }


    /***
     * 上传
     * @param file
     * @return
     * @throws IOException
     */
    @PostMapping("/upload")
    public  String upload(@RequestParam MultipartFile file) throws IOException {
        return fileLoadService.FileUpload(file);
    }

    /***
     * 文件下载
     */
    @GetMapping("/{fileUUID}")
    public void download(@PathVariable("fileUUID") String fileUUID, HttpServletResponse response) throws IOException {
        fileLoadService.download(fileUUID,response);
    }


}
