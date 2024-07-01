package com.example.entity;


import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "FileLoad对象", description = "文件表")
@TableName("sys_file")
@Component
public class FileLoad implements Serializable {
    @ApiModelProperty("文件Id")
    @TableId(type=IdType.AUTO)
    private Integer id;
    @ApiModelProperty("文件名")
    private String name;
    @ApiModelProperty("文件类型")
    private String type;
    @ApiModelProperty("文件大小")
    private Long size;
    @ApiModelProperty("文件下载链接")
    private String url;
    @ApiModelProperty("文件是否删除")
    @TableLogic
    private Integer isDelete;
    @ApiModelProperty("文件是否禁用")
    private Integer enable;
}
