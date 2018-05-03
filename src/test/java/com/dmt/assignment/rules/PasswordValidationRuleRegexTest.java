/**
 *
 */
package com.dmt.assignment.rules;

import com.dmt.assignment.config.AppConfig;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Test class for executing all the unit test cases for PasswordValidationRuleRegex class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class PasswordValidationRuleRegexTest {

    ValidationRule testValidationRuleEmptyRegex, testValidationRuleRegex;

    private static final String ERROR_MSG = "Regex cannot be null or empty.";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        testValidationRuleEmptyRegex = new PasswordValidationRuleRegex("", "testEmptyRegexRule", ERROR_MSG, true);
        testValidationRuleRegex = new PasswordValidationRuleRegex(".{3,10}", "testLengthRuleRegex", null, true);
    }

    @Test
    public void testRegexEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Regex cannot be null or empty.");
        testValidationRuleEmptyRegex.validate("sridhar123");

    }

    @Test
    public void testGetRuleRegex() {
        ((PasswordValidationRuleRegex) testValidationRuleRegex).setRegex(".{8,16}");
        assertEquals(".{8,16}", ((PasswordValidationRuleRegex) testValidationRuleRegex).getRegex());
    }

    @Test
    public void testRuleEnabled() {

        assertTrue("Rule Enabled : ", testValidationRuleRegex.isRuleEnabled());
    }


}
