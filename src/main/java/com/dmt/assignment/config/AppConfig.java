/**
 * 
 */
package com.dmt.assignment.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.dmt.assignment.rules.PasswordValidationRuleRegex;
import com.dmt.assignment.rules.ValidationRule;
import com.dmt.assignment.service.PasswordValidationService;
import com.dmt.assignment.service.ValidationService;

/**
 * @author Sridhar Yamsani
 *
 */
@Configuration
@PropertySource({ "classpath:validation-rules-messages.properties", "classpath:validation-rules.properties" })
public class AppConfig {

	@Autowired
	private Environment env;

	@Value("${lc.alphanum.rule.enabled}")
    private boolean isAlphaNumRuleEnabled;

    @Value("${lc.alpha.rule.enabled}")
    private boolean isAlphaRuleEnabled;

    @Value("${lc.num.rule.enabled}")
    private boolean isNumRuleEnabled;

    @Value("${length.rule.enabled}")
    private boolean isLengthRuleEnabled;

    @Value("${char.seq.rule.enabled}")
    private boolean isCharSeqRuleEnabled;

	@Bean
    @Qualifier("AlphaNumericRule")
	public ValidationRule lcAlphaNumberRule() {
        String alphaNumericMsg = env.getProperty("lc.alphanum.rule.error.msg");
		String alphaNumericRuleRegex = env.getProperty("lc.alphanum.rule.regex");
		String alphaNumericRuleName = env.getProperty("lc.alphanum.rule.name");
		return new PasswordValidationRuleRegex(alphaNumericRuleRegex, alphaNumericRuleName, alphaNumericMsg,isAlphaNumRuleEnabled);
	}

    @Bean
    @Qualifier("AlphaRule")
    public ValidationRule lcAlphaRule() {
       String alphaNumericMsg = env.getProperty("lc.alphanum.rule.error.msg");
        String alphaRuleRegex = env.getProperty("lc.alpha.rule.regex");
        String alphaRuleName = env.getProperty("lc.alpha.rule.name");
        return new PasswordValidationRuleRegex(alphaRuleRegex, alphaRuleName, alphaNumericMsg,isAlphaRuleEnabled);
    }
    @Bean
    @Qualifier("NumericRule")
    public ValidationRule lcNumberRule() {
        String alphaNumericMsg = env.getProperty("lc.alphanum.rule.error.msg");
        String numericRuleRegex = env.getProperty("lc.num.rule.regex");
        String numericRuleName = env.getProperty("lc.num.rule.name");
        return new PasswordValidationRuleRegex(numericRuleRegex, numericRuleName, alphaNumericMsg,isNumRuleEnabled);
    }

	@Bean
    @Qualifier("LengthRule")
	public ValidationRule lengthValidationRule() {
		String lengthErrorMsg = env.getProperty("length.rule.error.msg");
		String lengthRuleRegex = env.getProperty("length.rule.regex");
		String lengthRuleName = env.getProperty("length.rule.name");
		return new PasswordValidationRuleRegex(lengthRuleRegex, lengthRuleName, lengthErrorMsg,isLengthRuleEnabled);
	}

	@Bean
    @Qualifier("CharSequenceRule")
	public ValidationRule charSequenceRule() {
		String charSequenceErrorMsg = env.getProperty("char.seq.rule.error.msg");
		String charSequenceRuleRegex = env.getProperty("char.seq.rule.regex");
		String charSequenceRuleName = env.getProperty("char.seq.rule.name");
		return new PasswordValidationRuleRegex(charSequenceRuleRegex, charSequenceRuleName, charSequenceErrorMsg,isCharSeqRuleEnabled);
	}

	@Bean
	public ValidationService passwordValidationService() {
		return new PasswordValidationService();
	}

	@Bean
	public List<ValidationRule> validationRules() {
		List<ValidationRule> rules = new ArrayList();
        rules.add(lengthValidationRule());
		rules.add(lcAlphaNumberRule());
		rules.add(lcAlphaRule());
		rules.add(lcNumberRule());
		rules.add(charSequenceRule());
		return rules;
	}
}
