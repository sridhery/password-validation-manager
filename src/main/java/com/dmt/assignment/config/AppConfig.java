/**
 *
 */
package com.dmt.assignment.config;

import com.dmt.assignment.rules.PasswordSequenceValidationRule;
import com.dmt.assignment.rules.PasswordValidationRuleRegex;
import com.dmt.assignment.rules.ValidationRule;
import com.dmt.assignment.service.PasswordValidationService;
import com.dmt.assignment.service.ValidationService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sridhar Yamsani
 */
@Configuration
@PropertySource({"classpath:validation-rules.properties"})
public class AppConfig {

    @Value("${lc.alphanum.rule.enabled}")
    private boolean isAlphaNumRuleEnabled;

    @Value("${lc.alphanum.rule.regex}")
    private String alphaNumericRuleRegex;

    @Value("${lc.alphanum.rule.name}")
    private String alphaNumericRuleName;

    @Value("${lc.alphanum.rule.error.msg}")
    private String alphaNumericMsg;

    @Value("${length.rule.enabled}")
    private boolean isLengthRuleEnabled;

    @Value("${length.rule.regex}")
    private String lengthRuleRegex;

    @Value("${length.rule.name}")
    private String lengthRuleName;

    @Value("${length.rule.error.msg}")
    private String lengthErrorMsg;

    @Value("${char.seq.rule.enabled}")
    private boolean isCharSeqRuleEnabled;

    @Value("${char.seq.rule.name}")
    private String charSequenceRuleName;

    @Value("${char.seq.rule.error.msg}")
    private String charSequenceErrorMsg;

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    @Qualifier("AlphaNumericRule")
    public ValidationRule lcAlphaNumberRule() {
        return new PasswordValidationRuleRegex(alphaNumericRuleRegex, alphaNumericRuleName, alphaNumericMsg, isAlphaNumRuleEnabled);
    }

    @Bean
    @Qualifier("LengthRule")
    public ValidationRule lengthValidationRule() {
        return new PasswordValidationRuleRegex(lengthRuleRegex, lengthRuleName, lengthErrorMsg, isLengthRuleEnabled);
    }

    @Bean
    @Qualifier("CharSequenceRule")
    public ValidationRule charSequenceRule() {
        return new PasswordSequenceValidationRule(charSequenceRuleName, charSequenceErrorMsg, isCharSeqRuleEnabled);
    }

    @Bean
    public ValidationService passwordValidationService() {
        return new PasswordValidationService(validationRules());
    }

    @Bean
    public List<ValidationRule> validationRules() {
        List<ValidationRule> rules = new ArrayList();
        rules.add(lengthValidationRule());
        rules.add(lcAlphaNumberRule());
        rules.add(charSequenceRule());
        return rules;
    }
}
