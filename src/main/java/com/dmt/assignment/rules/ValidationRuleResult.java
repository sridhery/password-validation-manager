/**
 * 
 */
package com.dmt.assignment.rules;

/**
 * @author Sridhar Yamsani
 *
 */
public class ValidationRuleResult {

	private String validationRuleName;
	private String validationMessage;
	private boolean success;

	/**
	 * @return the validationMessage
	 */
	public String getValidationMessage() {
		return validationMessage;
	}

	/**
	 * @param validationMessage
	 *            the validationMessage to set
	 */
	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	/**
	 * @return the success
	 */
	public boolean isSuccess() {
		return success;
	}

	/**
	 * @param success
	 *            the success to set
	 */
	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * @return the validationRuleName
	 */
	public String getValidationRuleName() {
		return validationRuleName;
	}

	/**
	 * @param validationRuleName the validationRuleName to set
	 */
	public void setValidationRuleName(String validationRuleName) {
		this.validationRuleName = validationRuleName;
	}

}
