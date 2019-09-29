package fr.app.web.faces.validators;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpperCaseValidator extends PatternValidator implements ConstraintValidator<UpperCase, String> {

	/**
	 * Regex
	 */
	private static final Pattern upperCasePattern = Pattern.compile("[1-9A-Z_]*");

	
	/*
	 * (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#initialize(java.lang.annotation.Annotation)
	 */
	@Override
	public void initialize(UpperCase constraintAnnotation) {
		super.initialize();
	}

	/*
	 * (non-Javadoc)
	 * @see javax.validation.ConstraintValidator#isValid(java.lang.Object, javax.validation.ConstraintValidatorContext)
	 */
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return super.isValid(value);
	}

	/*
	 * (non-Javadoc)
	 * @see fr.cprpsncf.refdoc.web.views.edi.domaine.RegexValidator#getRegexPattern()
	 */
	@Override
	protected Pattern getRegexPattern() {
		return upperCasePattern;
	}


}
