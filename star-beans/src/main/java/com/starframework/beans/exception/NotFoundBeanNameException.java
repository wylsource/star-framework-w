package com.starframework.beans.exception;

import com.starframework.beans.BeansException;
import com.starframework.beans.annotation.Nullable;

/**
 * @Author: WuYuLong
 * @Date: Create in 19:11 2018/7/30
 * @DESC:
 */
public class NotFoundBeanNameException extends BeansException {

    public NotFoundBeanNameException(String msg) {
        super(msg);
    }
    public NotFoundBeanNameException(String msg, @Nullable Throwable cause){
        super(msg, cause);
    }
}
