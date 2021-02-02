package com.papaxiong.config;


import com.papaxiong.support.Wrapper;
import com.papaxiong.support.model.BusinessBaseException;
import com.papaxiong.support.model.LockedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhaoqi
 * @since 2020-01-14
 * 异常的处理
 */
@ControllerAdvice
@Slf4j
public class WrapperExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = LockedException.class)//悲观锁冲突
    public Object handleOnLock(HttpServletRequest request, LockedException e) {
        //log
        return Wrapper.error("99998", e.getMessage());
    }

    @ResponseBody
    @ExceptionHandler(value = BusinessBaseException.class)//业务类
    public Object handleBusiness(HttpServletRequest request, BusinessBaseException e) {
        //log
        e.printStackTrace();
        return Wrapper.error(e);
    }

    @ResponseBody
    @ExceptionHandler(value = Exception.class)//大的异常
    public Object handleSystem(HttpServletRequest request, Exception e) {
        //log
        e.printStackTrace();

        return Wrapper.error("99999", e instanceof RuntimeException ?
                e.getMessage() : String.format("系统异常[%s]", e.getMessage()));
    }

}
