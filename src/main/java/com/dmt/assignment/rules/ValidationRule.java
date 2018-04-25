/**
 * 
 */
package com.dmt.assignment.rules;

/**
 * Implement this validation rule to provide a new validation rule
 */
public interface ValidationRule {

	/**
	 * This method validates an input string
	 * 
	 * @param str
	 *            - The input string to be validated
	 * @return ValidationRuleResult if the validation fails
	 */
	ValidationRuleResult validate(String str);

}
