package com.tagsleuthregister.sleuth.services;


import com.tagsleuthregister.sleuth.services.SleuthService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Registry {

    private static final Logger LOGGER = LoggerFactory.getLogger(Registry.class);

    @Autowired
    private SleuthService sleuthService;

    @Before("@annotation(com.tagsleuthregister.sleuth.services.SleuthRegistry)")
    public void sendRequest(JoinPoint joinPoint) {
        LOGGER.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " + joinPoint.getSignature().getName());
        sleuthService.sendRequest("Request-" + joinPoint.getSignature().getName().toString(), joinPoint.getArgs());
        LOGGER.info("method: " + joinPoint.getSignature().getName());

    }


    @After("@annotation(com.tagsleuthregister.sleuth.services.SleuthRegistry)")
    public void sendResponse(JoinPoint joinPoint) {
        LOGGER.info("EEEEEEEEEEEEEEEEEEEEEEEEEEEE: " + joinPoint.getSignature().getName());
        sleuthService.sendResponse(joinPoint.getSignature().getName()+"-"+joinPoint.getArgs());
        LOGGER.info("method: " + joinPoint.getSignature().getName());
    }
}
