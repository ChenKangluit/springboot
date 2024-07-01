package com.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.entity.FileLoad;
import com.example.vo.PageFilesVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileLoadService extends IService<FileLoad> {

    /**
     * 文件分页查询
     * @param pageFileVo
     * @return
     */
    IPage<FileLoad> pageFile(PageFilesVo pageFileVo);

    /**
     * 文件是否禁用
     * @param fileLoad
     * @return
     */
    Integer isEnable(FileLoad fileLoad);

    /**
     * 文件上传
     * @param file
     * @return
     */
    String FileUpload(MultipartFile file);

    /**
     * 文件下载
     * @param fileUUID
     * @param response
     */
    void download(String fileUUID, HttpServletResponse response);
}
