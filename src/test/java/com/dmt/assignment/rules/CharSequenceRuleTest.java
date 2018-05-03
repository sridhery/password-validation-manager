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
 * Test class for executing all the unit test cases for related to the CharSequence Rule
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class CharSequenceRuleTest {

    @Autowired
    @Qualifier("CharSequenceRule")
    ValidationRule charSeqRuleRegex;

    @Test
    public void testCharSeqRulePassword() {
        assertTrue("Valid password sequence :", charSeqRuleRegex.validate("sridhar123").isSuccess());
    }

    @Test
    public void testPasswordForNoSeq() {
        assertTrue("Valid password sequence:", charSeqRuleRegex.validate("sridhar12").isSuccess());
    }

    @Test
    public void testPasswordForSeqNotImmediate() {
        assertTrue("Valid password sequence:", charSeqRuleRegex.validate("sridsri12").isSuccess());
    }

    @Test
    public void testPasswordForSingleCharSeq() {
        assertTrue("Valid password sequence:", charSeqRuleRegex.validate("ssridsri12").isSuccess());
    }

    @Test
    public void testPasswordForSeq() {
        assertFalse("Invalid password sequence:", charSeqRuleRegex.validate("srisri").isSuccess());
    }

    @Test
    public void testPasswordForSeqWithTrailingNum() {
        assertFalse("Invalid password sequence:", charSeqRuleRegex.validate("srisri1").isSuccess());
    }

    @Test
    public void testPasswordForSeqWithTrailingChar() {
        assertFalse("Invalid password sequence:", charSeqRuleRegex.validate("srisris").isSuccess());
    }

    @Test
    public void testPasswordForSeqWithLeadingChar() {
        assertFalse("Invalid password sequence:", charSeqRuleRegex.validate("ssrisri").isSuccess());
    }

    @Test
    public void testPasswordForSeqWithLeadingNum() {
        assertFalse("Invalid password sequence:", charSeqRuleRegex.validate("1srisri").isSuccess());
    }

    @Test
    public void testPasswordForFourCharSeq() {
        assertFalse("Invalid password sequence:", charSeqRuleRegex.validate("sridsrid12").isSuccess());
    }

    @Test
    public void testPasswordForSixCharSeq() {
        assertFalse("Invalid password sequence:", charSeqRuleRegex.validate("sridharsridhar").isSuccess());
    }

    @Test
    public void testPasswordForMixCharSeq() {
        assertFalse("Invalid password sequence:", charSeqRuleRegex.validate("asr@sr@1").isSuccess());
    }

    @Test
    public void testPasswordForSpecialCharSeq() {
        assertFalse("Invalid password sequence:", charSeqRuleRegex.validate("@@@@@@").isSuccess());
    }

    @Test
    public void testPasswordForNumericSeq() {
        assertFalse("Invalid password sequence:", charSeqRuleRegex.validate("123123").isSuccess());
    }
}
