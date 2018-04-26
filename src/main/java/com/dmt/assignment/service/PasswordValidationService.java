/**
 * 
 */
package com.dmt.assignment.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import com.dmt.assignment.rules.ValidationRule;
import com.dmt.assignment.rules.ValidationRuleResult;

/**
 * @author Sridhar Yamsani
 *
 */
public class PasswordValidationService implements ValidationService {
	private static final Logger LOGGER = LoggerFactory.getLogger(PasswordValidationService.class);

	@Autowired
	private List<ValidationRule> validationRules;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dmt.assignment.service.ValidationService#execute(java.lang.String)
	 */
	public List<ValidationRuleResult> execute(String password) {
		if (StringUtils.isEmpty(password)) {
			LOGGER.warn("Password cannot be null or empty.");
			throw new IllegalArgumentException("password cannot be empty or null");
		}
		List<ValidationRuleResult> validationRuleResults = new ArrayList();
		for (ValidationRule rule : getValidationRules()) {
			if(rule.isRuleEnabled()) {
				validationRuleResults.add(rule.validate(password));
			}
		}
		return validationRuleResults;
	}

	/**
	 * @return the validationRules
	 */
	public List<ValidationRule> getValidationRules() {
		return validationRules;
	}


}
