package fr.app.web.faces.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UpperCaseValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE})
@Documented
public @interface UpperCase {
	
	public String message() default "doit être en majuscule, sans espace, sans caractères spéciaux, le underscore est accepté\"";
	
	public Class<?>[] groups() default {};
	
	public Class<? extends Payload>[] payload() default{};
	

}
