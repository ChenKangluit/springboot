package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

    import lombok.AllArgsConstructor;
    import lombok.Builder;
    import lombok.Data;
    import lombok.NoArgsConstructor;


/**
 * <p>
 * 
 * </p>
 *
 * @author lee
 * @since 2022-06-09
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@TableName("sys_user_role")
@ApiModel(value = "UserRole对象", description = "")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("权限id")
    @TableField("role_id")
    private Integer roleId;


}
