package com.junjie.demo.exception;

import lombok.Getter;

/**
 * 自定义的一种异常
 */
@Getter
public class ServiceException extends RuntimeException{
    private String code;

    public ServiceException(String code,String msg){
        super(msg);
        this.code = code;
    }

}
