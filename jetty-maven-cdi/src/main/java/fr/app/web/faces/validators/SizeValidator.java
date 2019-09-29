package fr.app.web.faces.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang3.StringUtils;

public class SizeValidator implements ConstraintValidator<Size, String> {

	private static final Integer MIN_CAR = 3;
	
	@Override
	public void initialize(Size constraintAnnotation) {
		// Implémentation par défaut ignorée
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.isEmpty(value)) {
			return false;
		}
		
		
		return value.length() > MIN_CAR;
	}

}
