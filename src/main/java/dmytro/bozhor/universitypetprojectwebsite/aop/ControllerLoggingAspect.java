package dmytro.bozhor.universitypetprojectwebsite.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerLoggingAspect extends AbstractLoggingAspect {

    @Pointcut(value = "execution(public * *(..))")
    private void anyPublicMethod() {
    }

    @Pointcut(value = "within(dmytro.bozhor.universitypetprojectwebsite.controller..*)")
    private void withinPath() {
    }

    @Override
    @Before("anyPublicMethod() && withinPath()")
    public void logBeforeAdvice(JoinPoint joinPoint) {
        super.logBeforeAdvice(joinPoint);
    }

    @Override
    @AfterReturning("anyPublicMethod() && withinPath()")
    public void logAfterReturningAdvice(JoinPoint joinPoint) {
        super.logAfterReturningAdvice(joinPoint);
    }

    @Override
    @AfterThrowing(value = "anyPublicMethod() && withinPath()", throwing = "throwable")
    public void logAfterThrowingAdvice(JoinPoint joinPoint, Throwable throwable) {
        super.logAfterThrowingAdvice(joinPoint, throwable);
    }
}
