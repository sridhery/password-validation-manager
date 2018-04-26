/**
 *
 */
package com.dmt.assignment.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dmt.assignment.config.AppConfig;
import com.dmt.assignment.rules.ValidationRuleResult;
import com.dmt.assignment.service.PasswordValidationService;
import com.dmt.assignment.service.ValidationService;

/**
 * Client class for executing the Password Validation Service
 */
public class PasswordValidationClient {

    private static Logger LOGGER = LoggerFactory.getLogger(PasswordValidationClient.class);

    /**
     * @param args
     */
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ValidationService validationService = ctx.getBean(PasswordValidationService.class);
        List<ValidationRuleResult> validationRuleResults = validationService.execute("Sridhar123");
        for (ValidationRuleResult validationRuleResult : validationRuleResults) {
            LOGGER.info(String.format("Password validation rule name: | %s  | Status: | %s | Message:| %s%n |",
                    validationRuleResult.getValidationRuleName(),validationRuleResult.isSuccess(), validationRuleResult.getValidationMessage()));
        }

    }

}
