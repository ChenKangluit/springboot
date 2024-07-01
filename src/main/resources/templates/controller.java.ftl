package ${package.Controller};
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};

<#if restControllerStyle>
    import org.springframework.web.bind.annotation.RestController;
<#else>
    import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
    import ${superControllerClassPackage};
</#if>

/**
* <p>
    * ${table.comment!} 前端控制器
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if restControllerStyle>
    @RestController
<#else>
    @Controller
</#if>
@RequestMapping("<#if package.ModuleName?? && package.ModuleName != "">/${package.ModuleName}</#if>/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
<#if superControllerClass??>
    public class ${table.controllerName} extends ${superControllerClass} {
<#else>
    public class ${table.controllerName} {
</#if>

@Resource
private ${table.serviceName} ${table.entityPath}Service;
/**
* 添加或修改
* @param ${table.entityPath}
* @return
*/
@PostMapping
public Result save(@RequestBody ${entity} ${table.entityPath}) {
return Result.success(${table.entityPath}Service.saveOrUpdate(${table.entityPath}));
}
/**
* 批量删除
* @param ids
* @return
*/
@PostMapping("/del")
public Result delete(@RequestBody List<Integer> ids) {
    return Result.success(${table.entityPath}Service.removeByIds(ids));
    }
    /**
    * 查找全部
    * @return
    */
    @GetMapping
    public Result findAll() {
    return Result.success(${table.entityPath}Service.list());
    }
    /**
    * 查找一个
    * @param id
    * @return
    */
    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
    return Result.success(${table.entityPath}Service.getById(id));
    }
    /**
    * 分页查询
    * @param pageNum
    * @param pageSize
    * @return
    */
    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize,@RequestParam String keywords) {
    Page<${entity}> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<${entity}> wrapper = new LambdaQueryWrapper<>();
            wrapper
                .like(StringUtils.isNotEmpty(keywords),${entity}::,keywords);
    return Result.success(${table.entityPath}Service.page(page,wrapper));
    }

    }
    </#if>
