package kr.co.darkkaiser;

import org.aspectj.lang.ProceedingJoinPoint;

public class UppercaseAdvice {

	public Object translate(ProceedingJoinPoint thisJoinPoint) throws Throwable {
		String retVal = (String)thisJoinPoint.proceed();
		return retVal.toUpperCase();
	}

}
