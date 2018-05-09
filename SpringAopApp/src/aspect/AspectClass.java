package aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

public class AspectClass {

	@Around("allGetters()")
	public Object AroundAdvice(ProceedingJoinPoint proceedingJoinPoint) {
		Object returnedValue = null;
		try {
			System.err.println("\n[AspectClass@Around] Before advice");
			returnedValue = proceedingJoinPoint.proceed();
			System.err.println("[AspectClass@Around] After advice");
			
		} catch (Throwable e) {
			System.err.println("[AspectClass@Around] After Throwing");
		}
		
		System.err.println("[AspectClass@Around] After finally\n");
		
		return returnedValue;
	}
	
	public void customAdviceMethod() {
		System.err.println("\nCustom Advice Method called.\n");
	}
}
