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
        assertTrue("Valid password with sequence not immediate:", charSeqRuleRegex.validate("sridsri12").isSuccess());
    }

    @Test
    public void testPasswordForSingleCharSeq() {
        assertTrue("Valid password with single char sequence:", charSeqRuleRegex.validate("ssridsri12").isSuccess());
    }


    @Test
    public void testPasswordForTwoCharSeq() {
        assertFalse("Invalid password three char sequence:", charSeqRuleRegex.validate("sriridi").isSuccess());
    }

    @Test
    public void testPasswordForThreeCharSeq() {
        assertFalse("Invalid password three char sequence:", charSeqRuleRegex.validate("srisri").isSuccess());
    }

    @Test
    public void testPasswordForSeqWithTrailingNum() {
        assertFalse("Invalid password sequence with trailing number:", charSeqRuleRegex.validate("srisri1").isSuccess());
    }

    @Test
    public void testPasswordForSeqWithTrailingChar() {
        assertFalse("Invalid password sequence with trailing char:", charSeqRuleRegex.validate("srisris").isSuccess());
    }

    @Test
    public void testPasswordForSeqWithLeadingChar() {
        assertFalse("Invalid password sequence with leading char:", charSeqRuleRegex.validate("ssrisri").isSuccess());
    }

    @Test
    public void testPasswordForSeqWithLeadingNum() {
        assertFalse("Invalid password sequence with leading number:", charSeqRuleRegex.validate("1srisri").isSuccess());
    }

    @Test
    public void testPasswordForFourCharSeq() {
        assertFalse("Invalid four char sequence password:", charSeqRuleRegex.validate("sridsrid12").isSuccess());
    }

    @Test
    public void testPasswordForSixCharSeq() {
        assertFalse("Invalid six char sequence password:", charSeqRuleRegex.validate("sridharsridhar").isSuccess());
    }

    @Test
    public void testPasswordForMixCharSeq() {
        assertFalse("Invalid mix char sequence password:", charSeqRuleRegex.validate("asr@sr@1").isSuccess());
    }

    @Test
    public void testPasswordForSpecialCharSeq() {
        assertFalse("Invalid special char sequence password:", charSeqRuleRegex.validate("@@@@@@").isSuccess());
    }

    @Test
    public void testPasswordForNumericSeq() {
        assertFalse("Invalid numeric sequence password:", charSeqRuleRegex.validate("123123").isSuccess());
    }

    @Test
    public void testPasswordForBlankSeq() {
        assertFalse("Invalid white space sequence password:", charSeqRuleRegex.validate("ab    12se").isSuccess());
    }

    @Test
    public void testPasswordForLeadingBlankSeq() {
        assertFalse("Invalid white space sequence password:", charSeqRuleRegex.validate("      12se").isSuccess());
    }

    @Test
    public void testPasswordForTrailingBlankSeq() {
        assertFalse("Invalid white space sequence password:", charSeqRuleRegex.validate("12sr        ").isSuccess());
    }
}
