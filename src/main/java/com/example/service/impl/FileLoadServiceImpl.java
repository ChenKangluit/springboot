package com.example.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.common.Constants;
import com.example.entity.FileLoad;
import com.example.exception.ServiceException;
import com.example.mapper.FileLoadMapper;
import com.example.service.FileLoadService;
import com.example.vo.PageFilesVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

@Service
public class FileLoadServiceImpl extends ServiceImpl<FileLoadMapper, FileLoad> implements FileLoadService {

    @Value("${files.upload.path}")
    private String fileUploadPath;

    @Autowired
    private FileLoadMapper fileLoadMapper;

    @Override
    public IPage<FileLoad> pageFile(PageFilesVo pageFilesVo) {
        IPage<FileLoad> page = new Page<>(pageFilesVo.getPageNo(), pageFilesVo.getPageSize());
        LambdaQueryWrapper<FileLoad> wrapper = new LambdaQueryWrapper<>();

        wrapper
                .like(!ObjectUtil.isEmpty(pageFilesVo.getName()), FileLoad::getName, pageFilesVo.getName())
                .orderByDesc(FileLoad::getId);
        return  fileLoadMapper.selectPage(page, wrapper);
    }

    @Override
    public Integer isEnable(FileLoad fileLoad) {
        LambdaUpdateWrapper<FileLoad> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(FileLoad::getEnable,fileLoad.getEnable())
                .eq(FileLoad::getId,fileLoad.getId());
        return fileLoadMapper.update(null,wrapper);
    }

    @Override
    public String FileUpload(MultipartFile file) {
        //原文件名
        String originalFilename = file.getOriginalFilename();
        //文件类型
        String type = FileUtil.extName(originalFilename);
        //文件大小
        long fileSize = file.getSize();

        //存文件 磁盘地址
        File paramFile = new File(fileUploadPath);
        if(!paramFile.exists()){
            paramFile.mkdirs();
        }
        //更改文件名
        String uuid = IdUtil.fastSimpleUUID();
        String fileUUID =  uuid + StrUtil.DOT + type;
        File uploadfile = new File(fileUploadPath + fileUUID);
        //上传
        try {
            file.transferTo(uploadfile);
        } catch (IOException e) {
            throw new ServiceException(Constants.CODE_500,"文件上传失败");
        }

        //存数据库
        String url = "http://localhost:9090/file/" + fileUUID;
        FileLoad savefile = new FileLoad();
        savefile.setName(originalFilename);
        savefile.setType(type);
        savefile.setSize(fileSize/1024);
        savefile.setUrl(url);
        fileLoadMapper.insert(savefile);
        return url;
    }

    @Override
    public void download(String fileUUID, HttpServletResponse response) {
        //获取流
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        //设置响应头
        response.setContentType("application/octet-stream");
        response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileUUID,"UTF-8"));

        } catch (IOException e) {
            throw new ServiceException(Constants.CODE_500,"系统错误");
        }

        File filedown = new File(fileUploadPath + fileUUID);
        //读取
        try {
            out.write(FileUtil.readBytes(filedown));
        } catch (IOException e) {
            throw new ServiceException(Constants.CODE_401,"文件不存在");
        }finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
