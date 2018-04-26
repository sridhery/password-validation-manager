/**
 * 
 */
package com.dmt.assignment.rules;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * This is the main validation rule class where all the password validations are get executed
 *
 */
public class PasswordValidationRuleRegex extends AbstractPasswordValidationRule {

	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordValidationRuleRegex.class);
	private String regex;

	/**
	 * Parameterized Constructor
     *
	 * @param regex
	 * @param validationRuleName
	 * @param validationMessage
	 */
	public PasswordValidationRuleRegex(String regex, String validationRuleName, String validationMessage, boolean isEnabled) {
		super(validationRuleName, validationMessage,isEnabled);
		this.regex = regex;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dmt.assignment.rules.AbstractPasswordValidationRule#validatePassword(
	 * )
	 */
	@Override
	public boolean validatePassword(String password) {
		if (StringUtils.isEmpty(getRegex())) {
			LOGGER.warn("Regex cannot be null or empty.");
			throw new IllegalArgumentException("Regex cannot be null or empty.");
		}
		Pattern pattern = Pattern.compile(getRegex(),Pattern.UNICODE_CHARACTER_CLASS);
		Matcher matcher = pattern.matcher(password);
		return matcher.matches();
	}

	/**
	 * Getter method for retrieving the regex
	 * @return the regex
	 */
	public String getRegex() {
		return regex;
	}

	/**
	 * Setter method for setting the regex
	 * @param regex
	 *            the regex to set
	 */
	public void setRegex(String regex) {
		this.regex = regex;
	}

}
