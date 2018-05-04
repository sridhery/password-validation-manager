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
import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Sridhar Yamsani
 */
@Configuration
public class AppConfig {

    @Value("${validations.alphanumeric.enabled}")
    private boolean isAlphaNumRuleEnabled;

    @Value("${validations.alphanumeric.regex}")
    private String alphaNumericRuleRegex;

    @Value("${validations.alphanumeric.name}")
    private String alphaNumericRuleName;

    @Value("${validations.alphanumeric.message}")
    private String alphaNumericMsg;

    @Value("${validations.length.enabled}")
    private boolean isLengthRuleEnabled;

    @Value("${validations.length.regex}")
    private String lengthRuleRegex;

    @Value("${validations.length.name}")
    private String lengthRuleName;

    @Value("${validations.length.message}")
    private String lengthErrorMsg;

    @Value("${validations.charsequence.enabled}")
    private boolean isCharSeqRuleEnabled;

    @Value("${validations.charsequence.name}")
    private String charSequenceRuleName;

    @Value("${validations.charsequence.message}")
    private String charSequenceErrorMsg;

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer = new PropertySourcesPlaceholderConfigurer();
        YamlPropertiesFactoryBean yaml = new YamlPropertiesFactoryBean();
        yaml.setResources(new ClassPathResource("validation-rules.yml"));
        propertySourcesPlaceholderConfigurer.setProperties(yaml.getObject());
        return propertySourcesPlaceholderConfigurer;
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
