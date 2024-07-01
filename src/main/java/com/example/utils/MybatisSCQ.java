package com.example.utils;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;

import java.util.Collections;

public class MybatisSCQ {
    /**
     * 生成时   对应好 数据库驱动版本
     */
    //TODO 需要生成的表名
    private static final String tableName = "sys_user_role";
    //TODO 设置数据库
    private static final String databaseName = "generaltemplate-security";
    //TODO 设置数据库ip地址
    private static final String address = "localhost";
    //TODO 设置数据库端口
    private static final String port = "3306";
    //TODO 设置数据库账号
    private static final String username = "root";
    //TODO 设置数据库密码
    private static final String password = "root";
    //TODO 设置过滤表前缀
    private static final String filterName = "sys_";
    //TODO 设置作者
    private static final String authorName = "lee";
    //TODO 设置生成包名路径
    private static final String packageName = "com.example";

    public static void main(String[] args) {
        Generator();
    }
    public static void Generator(){
        FastAutoGenerator.create(
                        "jdbc:mysql://"+address+":"+port+"/"+databaseName+"?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=GMT%2B8",
                        username, password)
                .globalConfig(builder -> {
                    builder
                            .author(authorName) // 设置作者
                            .enableSwagger() // 开启 swagger 模式  可理解为接口文档规范
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() //禁止打开输出目录
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder
                            .parent(packageName) // 设置父包名
                            .moduleName("") // 设置父包模块名
                            .entity("entity")
                            .controller("controller")
                            .service("service")
                            .serviceImpl("service.impl")
                            .other("vue")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir") + "/src/main/resources/mapper/")); // 设置xml生成路径
                })
                .strategyConfig(builder -> {
                    builder
                            .addInclude(tableName) // ***设置需要生成的表名 不使用该方法默认生成全表
                            .addTablePrefix(filterName) // 设置过滤表前缀
                            .serviceBuilder()//Service配置
                            .formatServiceFileName("%sService")//%s = 表名  表名Service
                            .formatServiceImplFileName("%sServiceImp")//同上
                            .entityBuilder()//实体类配置 一般都是表名
                            .enableTableFieldAnnotation() //开启生成实体时生成字段注解
                            .enableLombok()//开启Lombok 开启 swagger 模式 就没必要使用这个
                            .versionColumnName("version")//乐观锁字段对应数据库那个字段
                            .versionPropertyName("version")//乐观锁字段对应实体类库那个字段
                            .logicDeleteColumnName("deleted")//逻辑删除字段对应数据库那个字段
                            .logicDeletePropertyName("deleted")//逻辑删除字段对应实体类库那个字段
                            .controllerBuilder()//Controller配置
                            .formatFileName("%sController")
                            .enableRestStyle()//开启生成@RestController控制器
                            .mapperBuilder()//mapper配置
                            .formatMapperFileName("%sMapper")
                            .formatXmlFileName("%sMapper")
                            .enableMapperAnnotation()//开启@Mapper
                            .superClass(BaseMapper.class);//继承的父类
                })
                .templateEngine(new MyFreemarkerTemplateEngine())
                .injectionConfig(builder -> {
//                    builder.customFile(Collections.singletonMap("vue.vue","/templates/vue.vue"));
                })
                .execute();
    }
}
