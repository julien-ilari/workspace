package fr.app.web.faces.interceptors;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import fr.app.web.faces.interceptors.annotations.Trace;

@Trace
@Interceptor
public class TraceInterceptor implements Serializable {

	private static final long serialVersionUID = 1L;

	@PostConstruct
	public void catchInitException(final InvocationContext ctx) throws Exception {

		ctx.proceed();

	}



	@AroundInvoke
	public Object catchException(final InvocationContext ctx) throws Throwable {
		System.out.println("TraceInterceptor catchException ");
			

		return ctx.proceed();

	}

}
