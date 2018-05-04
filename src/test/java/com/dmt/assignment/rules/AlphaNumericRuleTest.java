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
 * Test class for executing all the unit test cases for related to the AlphaNumeric Rule
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class AlphaNumericRuleTest {

    @Autowired
    @Qualifier("AlphaNumericRule")
    ValidationRule alphaNumericRuleRegex;

    @Test
    public void testAlphaNumRulePasswordEmpty() {
        assertFalse("Empty Password", alphaNumericRuleRegex.validate("").isSuccess());
    }

    @Test
    public void testAlphaNumRulePassword() {
        assertTrue("Valid alpha numeric password :", alphaNumericRuleRegex.validate("sridhar123").isSuccess());
    }


    @Test
    public void testPasswordTrailNum() {
        assertTrue("Valid alpha numeric password :", alphaNumericRuleRegex.validate("password123").isSuccess());
    }

    @Test
    public void testPasswordTrailChar() {
        assertTrue("Valid alpha numeric password :", alphaNumericRuleRegex.validate("123password").isSuccess());
    }

    @Test
    public void testPasswordMidNum() {
        assertTrue("Valid alpha numeric password :", alphaNumericRuleRegex.validate("pass123word").isSuccess());
    }

    @Test
    public void testPasswordWithSpecialChar() {
        assertFalse("Special Chars not allowed :", alphaNumericRuleRegex.validate("pa$sword1").isSuccess());
    }

    @Test
    public void testPasswordWithSpecialCharFirst() {
        assertFalse("Special Chars not allowed :", alphaNumericRuleRegex.validate("#pa$sword1").isSuccess());
    }

    @Test
    public void testPasswordWithCaps() {
        assertFalse("Capital letters not allowed :", alphaNumericRuleRegex.validate("PASS123").isSuccess());
    }

    @Test
    public void testPasswordWithCapsOnly() {
        assertFalse("Capital letters not allowed :", alphaNumericRuleRegex.validate("PASSWORD").isSuccess());
    }

    @Test
    public void testPasswordWithCapsNSpecialChars() {
        assertFalse("Capital letters not allowed :", alphaNumericRuleRegex.validate("PASS&^#$WORD").isSuccess());
    }

    @Test
    public void testPasswordForNum() {
        assertFalse("At least one numeric value required :", alphaNumericRuleRegex.validate("password").isSuccess());
    }

    @Test
    public void testPasswordForAlpha() {
        assertFalse("At least one lower case alpha required :", alphaNumericRuleRegex.validate("123456").isSuccess());
    }

    @Test
    public void testPasswordForBlankWithNum() {
        assertFalse("White spaces in middle not allowed :", alphaNumericRuleRegex.validate("123    456").isSuccess());
    }

    @Test
    public void testPasswordForBlankWithChar() {
        assertFalse("White spaces in middle not allowed:", alphaNumericRuleRegex.validate("abc    def").isSuccess());
    }

    @Test
    public void testPasswordForLeadingBlanks() {
        assertFalse("Leading white spaces not allowed :", alphaNumericRuleRegex.validate("    def").isSuccess());
    }

    @Test
    public void testPasswordForTrailingBlanks() {
        assertFalse("Trailing White spaces not allowed :", alphaNumericRuleRegex.validate("abc    ").isSuccess());
    }

    @Test
    public void testPasswordForSingleQuotes() {
        assertFalse("Empty quotes not allowed :", alphaNumericRuleRegex.validate("''").isSuccess());
    }

    @Test
    public void testPasswordForSingleQuotesChar() {
        assertFalse("Quotes not allowed :", alphaNumericRuleRegex.validate("'a'").isSuccess());
    }
}
