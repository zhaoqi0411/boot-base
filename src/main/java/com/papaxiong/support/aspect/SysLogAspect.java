//package com.papaxiong.support.aspect;
//
///**
// * @author zhaoqi
// * @since 2020-12-18
// */
//
//import com.alibaba.fastjson.JSONObject;
//
//import com.papaxiong.model.po.SysUserDO;
//import com.papaxiong.support.RequestUtils;
//import com.papaxiong.support.anno.SysLogAnno;
//import com.papaxiong.support.auth.AuthHolder;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.lang3.time.StopWatch;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.reflect.MethodSignature;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import javax.servlet.http.HttpServletRequest;
//import java.lang.reflect.Method;
//import java.util.Date;
//import java.util.Objects;
//import java.util.concurrent.TimeUnit;
//
//@Slf4j
//@Order
//@Component
//@Aspect
//public class SysLogAspect {
//
//    @Autowired
//    private SysLogDao sysLogDao;
//
//    @Around(value = "@annotation(com.papaxiong.support.anno.SysLogAnno)")
//    public Object invoke(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        //创建并启动StopWatch
//        StopWatch stopwatch = StopWatch.createStarted();
//
//        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
//        SysLogAnno logAnno = method.getAnnotation(SysLogAnno.class);
//
//        Long userId =-1L;
//        String userName="sys";
//
//        //操作人信息
//        SysUserDO sysUser = AuthHolder.getSysUser();
//        if (Objects.nonNull(sysUser)) {
//            userId = sysUser.getId();
//            userName = sysUser.getUserName();
//        }
//
//        //操作信息
//        Integer operateCode =null;
//        String operateName = "";
//        if(Objects.nonNull(logAnno)){
//            operateCode=logAnno.operateCode();
//            operateName=logAnno.operateName();
//        }
//
//        //方法名
//
//        String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
//
//        Object[] args = joinPoint.getArgs();
//        String params= JSONObject.toJSONString(args);
//
//
//        Object res = null;
//        try {
//            res = joinPoint.proceed(args);
//        } catch (Throwable throwable) {
//            throw throwable;
//        } finally {
//            stopwatch.stop();
//            long costTime = stopwatch.getTime(TimeUnit.MILLISECONDS);//毫秒
//
//            SysLogDO logDO=new SysLogDO();
//            logDO.setCostTime(costTime);
//            logDO.setUserId(userId);
//            logDO.setUserName(userName);
//            logDO.setOperationCode(operateCode);
//            logDO.setOperationName(operateName);
//            logDO.setMethod(methodName);
//            logDO.setParams(params);
//            logDO.setCostTime(costTime);
//            logDO.setSourceIp(RequestUtils.getIp(request));
//            logDO.setDateCreate(new Date());
//            sysLogDao.save(logDO);
//        }
//        return res;
//
//    }
//
//
//}
