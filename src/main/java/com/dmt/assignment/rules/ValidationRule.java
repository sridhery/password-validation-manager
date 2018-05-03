/**
 *
 */
package com.dmt.assignment.rules;

/**
 * Base Interface for implementing validation rules
 */
public interface ValidationRule {

    /**
     * This method validates an input string
     *
     * @param str - The input string to be validated
     * @return ValidationRuleResult if the validation fails
     */
    ValidationRuleResult validate(String str);

    /**
     * Returns the boolean value to know the rule enable status
     *
     * @return boolean
     */
    boolean isRuleEnabled();

}
