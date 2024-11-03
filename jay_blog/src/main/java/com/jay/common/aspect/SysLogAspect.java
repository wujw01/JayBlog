package com.jay.common.aspect;

import com.alibaba.fastjson.JSON;
import com.jay.common.annotation.SysLog;
import com.jay.common.util.HttpContextUtils;
import com.jay.common.util.IpUtil;
import com.jay.modules.sys.entity.MtoLog;
import com.jay.modules.sys.service.MtoLogService;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * 系统日志切面
 * Created by xiang.wei on 2018/10/17
 *
 * @author xiang.wei
 */
@Aspect
@Component
public class SysLogAspect {
    @Autowired
    private MtoLogService mtoLogService;

    /**
     * 定义日志切点
     */
    @Pointcut("@annotation(com.jay.common.annotation.SysLog)")
    public void logPointCut() {

    }

    /**
     * 环绕通知
     * @param point
     * @return
     */
    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object proceed = point.proceed();
//        执行时长
        long time = System.currentTimeMillis() - startTime;
        saveSysLog(point, time);
        return proceed;
    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        MtoLog mtoLog = new MtoLog();
//      获取注解内容
        SysLog annotation = method.getAnnotation(SysLog.class);
        if (annotation != null) {
            mtoLog.setOperation(annotation.value());
        }
//        获取类名
        String className = joinPoint.getTarget().getClass().getName();
//        获取方法名
        String methodName = method.getName();
        mtoLog.setMethod(className + "." + methodName + "()");
//        获取用户名
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        mtoLog.setUsername(username);
//       获取参数
        Object[] args = joinPoint.getArgs();
        if (args != null) {
            String param = JSON.toJSONString(args[0]);
            mtoLog.setParams(param);
        }
        mtoLog.setTime(time);
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        mtoLog.setIp(IpUtil.getIpAddr(request));

        mtoLog.setCreateDate(new Date());
        mtoLogService.insert(mtoLog);
    }
}
