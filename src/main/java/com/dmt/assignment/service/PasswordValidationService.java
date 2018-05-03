/**
 *
 */
package com.dmt.assignment.service;

import com.dmt.assignment.rules.ValidationRule;
import com.dmt.assignment.rules.ValidationRuleResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This is the main service class where list of available validations get executed.
 */
public class PasswordValidationService implements ValidationService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordValidationService.class);

    private List<ValidationRule> validationRules;

    @Autowired
    public PasswordValidationService(List<ValidationRule> validationRules) {
        this.validationRules = validationRules.stream().filter(ValidationRule::isRuleEnabled).collect(Collectors.toList());
    }

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
            validationRuleResults.add(rule.validate(password));
        }
        return validationRuleResults;
    }

    /**
     * Returns the list of validation rules
     *
     * @return the validationRules
     */
    public List<ValidationRule> getValidationRules() {
        return validationRules;
    }


}
