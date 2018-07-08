package com.starframework.beans;

import com.sun.istack.internal.Nullable;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/7 09:29
 * @Description: 定义bean的相关异常
 */
public abstract class BeansException extends RuntimeException{

    public BeansException(String msg){
        super(msg);
    }

    public BeansException(String msg, @Nullable Throwable cause){
        super(msg, cause);
    }

}
