package com.example.credit_service_project.configurations;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingConfiguration {

    @Pointcut("execution(public * com.example.credit_service_project.controllers.*.*(..))")
    public void controllerLog() {
    }

    @Pointcut("execution(public * com.example.credit_service_project.services.*.*(..))")
    public void serviceLog() {
    }

    @Before("controllerLog()")
    public void doBeforeControllerLog(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();
        log.info("""
                        NEW REQUEST:
                        IP : {}
                        URL : {}
                        HTTP_METHOD : {}
                        CONTROLLER_METHOD : {}.{}""",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName());
    }

    @Before("serviceLog()")
    public void doBeforeServiceLog(JoinPoint joinPoint) {
        log.info("""
                        RUN SERVICE:
                        SERVICE_METHOD: {}.{}""",
                joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturn(Object returnObject) {
        log.info("""
                Return value: {}
                END OF REQUEST
                """, returnObject);
    }

    @AfterThrowing(throwing = "exception", pointcut = "controllerLog()")
    public void throwException(JoinPoint joinPoint, Exception exception) {
        log.error(" Request throw an exception. Cause - {}.{}",
                Arrays.toString(joinPoint.getArgs()), exception.getMessage());
    }

}
