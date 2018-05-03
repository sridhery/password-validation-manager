/**
 *
 */
package com.dmt.assignment.rules;

/**
 * This is an immutable class which holds the validation rule results.
 */
public final class ValidationRuleResult {

    private String validationRuleName;
    private String validationMessage;
    private boolean success;

    public ValidationRuleResult(String validationRuleName, boolean success, String validationMessage) {
        this.validationRuleName = validationRuleName;
        this.validationMessage = validationMessage;
        this.success = success;
    }

    /**
     * @return the validationMessage
     */
    public String getValidationMessage() {
        return validationMessage;
    }


    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }


    /**
     * @return the validationRuleName
     */
    public String getValidationRuleName() {
        return validationRuleName;
    }


}
