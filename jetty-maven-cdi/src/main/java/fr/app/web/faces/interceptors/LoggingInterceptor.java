package fr.app.web.faces.interceptors;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import fr.app.web.faces.interceptors.annotations.Log;

/**
 * The abstract Class AbstractExceptionInterceptor.
 */
@Log @Interceptor
public class LoggingInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;


	@PostConstruct
	public void catchInitException(final InvocationContext ctx) throws Exception {
		
		ctx.proceed();	
	}

	@AroundInvoke
	public Object catchException(final InvocationContext ctx) throws Throwable  {
		System.out.println("Logging catchException ");
		
		
		System.out.println("Entering method: " + ctx.getMethod().getName());
		return ctx.proceed();
		
	}
}
