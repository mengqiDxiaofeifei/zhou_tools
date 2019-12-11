package com.dabaiyang.tools.aop;

import com.dabaiyang.tools.utils.HttpUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SystemCounterAspect {


    // controller层切点
    @Pointcut("@annotation(com.dabaiyang.tools.annotion.SystemControllerCounter)")
    public void serviceAspect() {
    }

    @Before("serviceAspect()")
    public void doServiceBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求的参数
        String methodName = (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()");
        System.out.println("methodName = " + methodName);
        if (HttpUtils.queryCounter(methodName)) {
            HttpUtils.addCounter(methodName, "1");
        } else {
            HttpUtils.createCounter(methodName, "1");
        }
    }
}
