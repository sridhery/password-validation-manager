/**
 * 
 */
package com.dmt.assignment.client;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.dmt.assignment.config.AppConfig;
import com.dmt.assignment.rules.ValidationRuleResult;
import com.dmt.assignment.service.PasswordValidationService;
import com.dmt.assignment.service.ValidationService;

/**
 * @author Sridhar Yamsani
 *
 */
public class PasswordValidationClient {


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
		ValidationService validationService = ctx.getBean(PasswordValidationService.class);
		List<ValidationRuleResult> ruleResult = validationService.execute("srisri1");
		for (ValidationRuleResult result : ruleResult) {
			System.out.println(String.format("Password rule %s validity is %s with message %s%n",
					result.getValidationRuleName(), result.isSuccess(), result.getValidationMessage()));
		}

	}

}
