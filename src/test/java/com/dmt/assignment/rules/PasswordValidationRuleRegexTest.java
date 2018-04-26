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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for executing all the unit test cases for PasswordValidationRuleRegex class
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

    ValidationRule testValidationRuleEmptyRegex, testValidationRuleRegex;

    private static final String ERROR_MSG = "Regex cannot be null or empty.";


    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void setUp() {
        testValidationRuleEmptyRegex = new PasswordValidationRuleRegex("", "testEmptyRegexRule", ERROR_MSG, true);
        testValidationRuleRegex = new PasswordValidationRuleRegex(".{3,10}","testLengthRuleRegex",null,true);
    }

    @Test
    public void testRegexEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Regex cannot be null or empty.");
        testValidationRuleEmptyRegex.validate("sridhar123");

    }

    @Test
    public void testGetRuleRegex(){
        ((PasswordValidationRuleRegex)testValidationRuleRegex).setRegex(".{8,16}");
        assertEquals(".{8,16}",((PasswordValidationRuleRegex)testValidationRuleRegex).getRegex());
    }

    @Test
    public void testRuleEnabled(){

        assertTrue("Rule Enabled : ",testValidationRuleRegex.isRuleEnabled());
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
