/**
 *
 */
package com.dmt.assignment.rules;

import com.dmt.assignment.config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Sridhar Yamsani
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class PasswordValidationRuleRegexTest {

    @Autowired
    @Qualifier("AlphaNumericRule")
    ValidationRule alphaNumericRuleRegex;

    @Autowired
    @Qualifier("AlphaRule")
    ValidationRule alphaRuleRegex;

    @Autowired
    @Qualifier("NumericRule")
    ValidationRule numericRuleRegex;

    @Autowired
    @Qualifier("LengthRule")
    ValidationRule lengthRuleRegex;

    @Autowired
    @Qualifier("CharSequenceRule")
    ValidationRule charSeqRuleRegex;

    ValidationRule testValidationRule;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        testValidationRule = new PasswordValidationRuleRegex("", "testEmptyRegexRule", "Regex cannot be null or empty" +
                ".");
    }

    @Test
    public void testPasswordEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Regex cannot be null or empty.");
        testValidationRule.validate("sridhar123");

    }

    @Test
    public void testAlphaNumRulePasswordEmpty() {
        assertFalse("Empty Password", alphaNumericRuleRegex.validate("").isSuccess());
    }

    @Test
    public void testAlphaNumRulePassword(){
        assertTrue("Valid password :" ,alphaNumericRuleRegex.validate("sridhar123").isSuccess() );
    }

    @Test
    public void testAlphaRulePassword(){
        assertTrue("Valid password :" ,alphaRuleRegex.validate("sridhar123").isSuccess() );
    }

    @Test
    public void testNumRulePassword(){
        assertTrue("Valid password :" ,numericRuleRegex.validate("sridhar123").isSuccess() );
    }

    @Test
    public void testLengthRulePassword(){
        assertTrue("Valid password :" ,lengthRuleRegex.validate("sridhar123").isSuccess() );
    }

    @Test
    public void testCharSeqRulePassword(){
        assertTrue("Valid password :" ,charSeqRuleRegex.validate("sridhar123").isSuccess() );
    }

}
