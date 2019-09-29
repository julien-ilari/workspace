package fr.app.web.faces.validators;

import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmptyValidator implements ConstraintValidator<Empty, List<?>>  {

	
	@Override
	public void initialize(Empty constraintAnnotation) {
		//
	}

	@Override
	public boolean isValid(List<?> value, ConstraintValidatorContext context) {
		return value!=null&&!value.isEmpty(); 
	}
	
	

}
