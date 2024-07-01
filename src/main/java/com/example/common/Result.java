package com.example.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 接口统一返回工具类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private Integer code;
    private String message;
    private Object data;
    private boolean ok;

    public static Result success(){
        return new Result(Constants.CODE_200,"成功",null,true);
    }
    public static Result success(Object data){
        return new Result(Constants.CODE_200,"成功",data,true);
    }

    public static Result error(Integer code,String msg){
        return new Result(code,msg,null,false);
    }

    public static Result error(){
        return new Result(Constants.CODE_500,"系统错误",null,false);
    }


}
