package com.hks.spring_boot_hibernate.exception;

import com.hks.spring_boot_hibernate.enums.ResultEnum;

/**
 * @Author: xiaohe
 * @Date: 2018/7/21 20:37
 * @Description: 自定义异常类
 */
public class UserException extends RuntimeException{

    private Integer code;

    public UserException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }
}
