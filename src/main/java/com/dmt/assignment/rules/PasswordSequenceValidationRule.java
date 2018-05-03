package com.dmt.assignment.rules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PasswordSequenceValidationRule extends AbstractPasswordValidationRule {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordSequenceValidationRule.class);

    /**
     * Parameterized constructor
     *
     * @param validationRuleName
     * @param validationMessage
     * @param isEnabled
     */
    public PasswordSequenceValidationRule(String validationRuleName, String validationMessage, boolean isEnabled) {
        super(validationRuleName, validationMessage, isEnabled);
    }

    /**
     * Implements this method to validate the password string
     *
     * @param password
     * @return
     */
    @Override
    public boolean validatePassword(String password) {
        for (int i = 0; i < password.length(); i++) {
            char c1 = password.charAt(i);
            int nextIndex = password.indexOf(c1, i + 2);

            while (nextIndex != -1) {
                String s1 = password.substring(i, nextIndex);
                if (2 * nextIndex - i <= password.length()) {
                    String s2 = password.substring(nextIndex, 2 * nextIndex - i);
                    if (s1.equals(s2)) {
                        return false;
                    }
                }
                nextIndex = password.indexOf(c1, nextIndex + 1);
            }

        }
        return true;
    }
}
