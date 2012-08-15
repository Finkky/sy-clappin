package cz.cvut.fit.culkajac.dp.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

//
@Interceptor
@Logged
public class LoggedInterceptor {
	private static final Logger LOGGER = LoggerFactory.getLogger(LoggedInterceptor.class);

	@AroundInvoke
	public Object invoker(InvocationContext joinPoint) throws Exception {
		LOGGER.info("Class: {}, Method: {}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getMethod().getName());
		return joinPoint.proceed();
	}

}
