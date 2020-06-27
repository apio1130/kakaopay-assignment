package com.assignment.kakaopay.common.aop;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.assignment.kakaopay.service.model.User;

@Aspect
@Component
public class UserInfoAspect {

    private static final String HEADER_ROOM_ID = "X-ROOM-ID";
    private static final String HEADER_USER_ID = "X-USER-ID";

    @Around("execution(* *(.., @UserInfo (*), ..))")
    public Object findUserInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest servletRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = servletRequest.getHeader(HEADER_USER_ID);
        String roomId = servletRequest.getHeader(HEADER_ROOM_ID);

        Object[] args = Arrays.stream(joinPoint.getArgs()).map(a -> {
            if (a instanceof User && (userId != null && roomId != null)) {
                a = new User(userId, roomId);
            }
            return a;
        }).toArray();

        return joinPoint.proceed(args);
    }

}
