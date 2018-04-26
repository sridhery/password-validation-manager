/**
 * 
 */
package com.dmt.assignment.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for password validation rules
 */
public abstract class AbstractPasswordValidationRule implements ValidationRule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractPasswordValidationRule.class);
	
	private String validationRuleName;
	private String validationMessage;
	private boolean isEnabled;

	
	/**
	 * Parameterized constructor
	 * 
	 * @param validationRuleName
	 * @param validationMessage
	 */
	public AbstractPasswordValidationRule(String validationRuleName, String validationMessage, boolean isEnabled){
		this.validationRuleName = validationRuleName;
		this.validationMessage = validationMessage;
        this.isEnabled = isEnabled;
	}
	
	/**
	 * Default implementation for the password string validation
	 */
	public ValidationRuleResult validate(String password) {
		LOGGER.debug("Validating password with {} validation rule", validationRuleName);
		boolean isSuccess = validatePassword(password);
		LOGGER.debug("Result of {} validation rule is {}", validationRuleName, isSuccess ? "Success" : "Fail");
		ValidationRuleResult ruleResult = new ValidationRuleResult();
	        ruleResult.setSuccess(isSuccess);
	        ruleResult.setValidationRuleName(validationRuleName);
	        if (!isSuccess) {
	            ruleResult.setValidationMessage(validationMessage);
	        }
	        return ruleResult;
	}

	/**
	 * Implements this method to validate the password string
	 * 
	 * @param password
	 * @return
	 */
	public abstract boolean validatePassword(String password);

    @Override
    public boolean isRuleEnabled() {
        return isEnabled;
    }
}
