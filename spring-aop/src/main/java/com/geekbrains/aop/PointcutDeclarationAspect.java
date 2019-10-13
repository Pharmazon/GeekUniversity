package com.geekbrains.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect
//@Component
public class PointcutDeclarationAspect {

    @Pointcut("execution(public * com.geekbrains.aop.UserDAO.get*(..))") // pointcut expression
    public void userDAOGetTrackerPointcut() {
    }

    @Pointcut("execution(public * com.geekbrains.aop.UserDAO.set*(..))") // pointcut expression
    public void userDAOSetTrackerPointcut() {
    }

    @Pointcut("userDAOGetTrackerPointcut() || userDAOSetTrackerPointcut()") // pointcut expression
    public void userDAOGetOrSetTrackerPointcut() {
    }

    @Before("userDAOGetOrSetTrackerPointcut()") // || && !
    public void userDAOGetOrSetTracker() {
        System.out.println("В классе UserDAO вызывают геттер или сеттер");
    }
}
