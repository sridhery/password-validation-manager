/**
 *
 */
package com.dmt.assignment.service;

import com.dmt.assignment.rules.ValidationRuleResult;

import java.util.List;

/**
 * This method validates password using the rules configured in system
 */
public interface ValidationService {

    /**
     * This method validates password using the rules configured in system
     *
     * @param str - Input String
     * @return List<ValidationRuleResult> List of Validation Rule Results
     */
    List<ValidationRuleResult> execute(String str);

}
