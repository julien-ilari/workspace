package fr.app.web.faces.interceptors;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import fr.app.web.faces.interceptors.annotations.LogInterceptor;

/**
 * The abstract Class AbstractExceptionInterceptor.
 */
@LogInterceptor @Interceptor
public class LoggingInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;


	@PostConstruct
	public void catchInitException(final InvocationContext ctx) throws Exception {
		
		ctx.proceed();	
	}

	@AroundInvoke
	public Object catchException(final InvocationContext ctx) throws Throwable  {
		System.out.println("Logging catchException ");
		
		long debutExec = System.currentTimeMillis();
	    try {
	      return ctx.proceed();
	    } finally {
	      long tempsExec = System.currentTimeMillis() - debutExec;
	      System.out.println("[PERF] Temps d'execution de la methode " + ctx.getClass() 
	        + "." + ctx.getMethod() + " : " + tempsExec + " ms");
	      
	     
	    }
		
	}
}
