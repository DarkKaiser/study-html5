package kr.co.darkkaiser;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class DoAspect {
	
	@Pointcut("execution(public * kr.co.darkkaiser.*Impl.*(..))")
	private void doBeforePointcut() {};

	@Before("doBeforePointcut()")
    public void doBefore(JoinPoint joinPoint) {
        System.out.println("***AspectJ*** doBefore() is running!! intercepted : " + joinPoint.getSignature().getName());
    }

	@After("execution(public * kr.co.darkkaiser.*Impl.*(..))")
	public void doAfter(JoinPoint joinPoint) {
		System.out.println("***AspectJ*** doAfter() is running!! intercepted : " + joinPoint.getSignature().getName());
    }
	
	@AfterReturning(pointcut="execution(public * kr.co.darkkaiser.*Impl.*(..))", returning="retVal")
	public void doAfterReturning(JoinPoint joinPoint, Object retVal) {
		System.out.println("***AspectJ*** doAfterReturning() is running!! intercepted : " + joinPoint.getSignature().getName());
	}
	
	@AfterThrowing(pointcut="execution(public * kr.co.darkkaiser.*Impl.*(..))", throwing="exception")
	public void doAfterThrowing(JoinPoint joinPoint, Exception exception) {
		System.out.println("***AspectJ*** doAfterThrowing() is running!! intercepted : " + joinPoint.getSignature().getName());
	}
	
	@Around("execution(public * kr.co.darkkaiser.*Impl.*(..))")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		System.out.println("***AspectJ*** doAround() is running!! before intercepted : " + joinPoint.getSignature().getName());
		
		Object retVal = joinPoint.proceed();
		
		System.out.println("***AspectJ*** doAround() is running!! after intercepted : " + joinPoint.getSignature().getName());
		
		return retVal;
    }
    
}
