package fr.app.web.faces.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public abstract class PatternValidator  {
	
	/**
	 * Initialisation
	 */
	public void initialize() {

	}

	public boolean isValid(String value) {
		if (!StringUtils.isEmpty(value)) {
			Matcher matcher = getRegexPattern().matcher(value);
			if (!matcher.matches()) {
				return false;
			}
		}
		
		return true;
	}

	protected abstract Pattern getRegexPattern();

}
