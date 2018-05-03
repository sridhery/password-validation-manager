package com.dmt.assignment.rules;

import com.dmt.assignment.config.AppConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test class for executing all the unit test cases for Password Length Rule
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class LengthRuleTest {


    @Autowired
    @Qualifier("LengthRule")
    ValidationRule lengthRuleRegex;

    @Test
    public void testLengthRulePasswordWhiteSpcae() {
        assertFalse("Empty Password :", lengthRuleRegex.validate("").isSuccess());
    }

    @Test
    public void testLengthRulePassword() {
        assertTrue("Valid password length:", lengthRuleRegex.validate("sridhar123").isSuccess());
    }

    @Test
    public void testPasswordForInvalidMinLength() {
        assertFalse("Invalid password min length:", lengthRuleRegex.validate("a234").isSuccess());
    }

    @Test
    public void testPasswordForInvalidMaxLength() {
        assertFalse("Invalid password max length:", lengthRuleRegex.validate("abcdefghijklm").isSuccess());
    }

    @Test
    public void testPasswordForLengthMinBoundary() {
        assertTrue("Valid password length:", lengthRuleRegex.validate("a2345").isSuccess());
    }

    @Test
    public void testPasswordForLengthMinBoundaryWithBlank() {
        assertTrue("Valid password length:", lengthRuleRegex.validate("a2 45").isSuccess());
    }

    @Test
    public void testPasswordLengthMaxBoundary() {
        assertTrue("Valid password length:", lengthRuleRegex.validate("abcdefghijkl").isSuccess());
    }

    @Test
    public void testPasswordWithMidLength() {
        assertTrue("Valid password length:", lengthRuleRegex.validate("abcdef").isSuccess());
    }

    @Test
    public void testPasswordLengthWithSpecialChars() {
        assertTrue("Valid password length:", lengthRuleRegex.validate("#%$%$#%$#%#").isSuccess());
    }

    @Test
    public void testPasswordLengthWithLeadingBlanks() {
        assertFalse("Invalid password length:", lengthRuleRegex.validate("              abc12").isSuccess());
    }

    @Test
    public void testPasswordLengthWithTrailingBlanks() {
        assertFalse("Invalid password length:", lengthRuleRegex.validate("12s                 ").isSuccess());
    }

    @Test
    public void testPasswordLengthWithMidBlanks() {
        assertFalse("Invalid password length:", lengthRuleRegex.validate("12s                 sa").isSuccess());
    }

    @Test
    public void testPasswordLengthWithBlanks() {
        assertFalse("Invalid password length:", lengthRuleRegex.validate("                        ").isSuccess());
    }

}
