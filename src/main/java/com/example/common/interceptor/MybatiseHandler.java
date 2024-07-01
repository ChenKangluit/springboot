package com.example.common.interceptor;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Slf4j
@Component
public class MybatiseHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("create time");
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
//        this.setFieldValByName("createTime",new Date(),metaObject);
//        this.setFieldValByName("updateTime",new Date(),metaObject);

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("update time");
        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now()); // 起始版本 3.3.0(推荐)
//        this.setFieldValByName("updateTime",new Date(),metaObject);
    }
}
