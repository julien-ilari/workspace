package fr.app.web.faces.interceptors.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.interceptor.InterceptorBinding;

@InterceptorBinding
@Retention(RUNTIME)
@Target({ TYPE, METHOD })
public @interface Trace {

	
	@Nonbinding Action action() default Action.INSERT;
	
	/** Enumération des différents niveaux d'action. */
	public static enum Action { INSERT, UPDATE, DELETE };
}
