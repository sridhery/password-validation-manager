/**
 *
 */
package com.dmt.assignment.service;

import com.dmt.assignment.config.AppConfig;
import com.dmt.assignment.rules.ValidationRuleResult;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Test class for executing all the unit test cases for PasswordValidationService class
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class, loader = AnnotationConfigContextLoader.class)
public class PasswordValidationServiceTest {

    private static final String ALPHA_NUMERIC_RULE = "AlphaNumericRule";
    private static final String LENGTH_RULE = "LengthRule";
    private static final String SEQUENCE_RULE = "CharSequenceRule";
    private static final String ALPHA_NUMERIC_RULE_MSG = "Must consist of a mixture of lowercase letters and " +
            "numerical digits only, with at least one of each.";
    private static final String LENGTH_RULE_MSG = "Must be between 5 and 12 characters in length.";
    private static final String SEQUENCE_RULE_MSG = "Must not contain any sequence of characters immediately followed" +
            " by the same sequence.";


    @Autowired
    ValidationService validationService;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testPasswordEmpty() {
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("password cannot be empty or null");
        validationService.execute("");

    }

    @Test
    public void testPasswordErrorMsgForTrailNum() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("password123");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            assertNull("NULL is expected here : ", validationRuleResult.getValidationMessage());
        }
    }

    @Test
    public void testPasswordMsgForTrailChar() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("123password");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            assertNull("NULL is expected here : ", validationRuleResult.getValidationMessage());
        }
    }

    @Test
    public void testPasswordErrorMsgForMidNum() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("pass123word");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            assertNull("NULL is expected here : ", validationRuleResult.getValidationMessage());
        }
    }

    @Test
    public void testPasswordErrorMsgForSpecialChar() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("pa$sword1");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (ALPHA_NUMERIC_RULE.equals(validationRuleResult.getValidationRuleName())) {
                Assert.assertEquals(ALPHA_NUMERIC_RULE_MSG, validationRuleResult.getValidationMessage());
            }
        }

    }

    @Test
    public void testPasswordErrorMsgForCaps() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("PASS123");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (ALPHA_NUMERIC_RULE.equals(validationRuleResult.getValidationRuleName())) {
                Assert.assertEquals(ALPHA_NUMERIC_RULE_MSG, validationRuleResult.getValidationMessage());
            }
        }

    }

    @Test
    public void testPasswordErrorMsgForNum() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("password");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (ALPHA_NUMERIC_RULE.equals(validationRuleResult.getValidationRuleName())) {
                Assert.assertEquals(ALPHA_NUMERIC_RULE_MSG, validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsgForAlpha() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("123456");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (ALPHA_NUMERIC_RULE.equals(validationRuleResult.getValidationRuleName())) {
                Assert.assertEquals(ALPHA_NUMERIC_RULE_MSG, validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsgForMinLength() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("a234");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (LENGTH_RULE.equals(validationRuleResult.getValidationRuleName())) {
                Assert.assertEquals(LENGTH_RULE_MSG, validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsgForMaxLength() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("a234567891011121");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (LENGTH_RULE.equals(validationRuleResult.getValidationRuleName())) {
                Assert.assertEquals(LENGTH_RULE_MSG, validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsgForMinLengthBoundary() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("a2345");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (LENGTH_RULE.equals(validationRuleResult.getValidationRuleName())) {
                assertNull("NULL is expected here : ", validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsgForMaxLengthBoundary() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("abcdefghijkl");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (LENGTH_RULE.equals(validationRuleResult.getValidationRuleName())) {
                assertNull("NULL is expected here : ", validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsgForMaxLengthExceeded() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("abcdefghijklmn");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (LENGTH_RULE.equals(validationRuleResult.getValidationRuleName())) {
                assertEquals(LENGTH_RULE_MSG, validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsgForSeq() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("srisri123");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (SEQUENCE_RULE.equals(validationRuleResult.getValidationRuleName())) {
                Assert.assertEquals(SEQUENCE_RULE_MSG, validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsfForNoSeq() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("sridhar12");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (SEQUENCE_RULE.equals(validationRuleResult.getValidationRuleName())) {
                assertNull("NULL is expected here : ", validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsgForNoImmediateSeq() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("sridsri12");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (SEQUENCE_RULE.equals(validationRuleResult.getValidationRuleName())) {
                assertNull("NULL is expected here : ", validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsgForSingleCharSeq() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("ssridsri12");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (SEQUENCE_RULE.equals(validationRuleResult.getValidationRuleName())) {
                assertNull("NULL is expected here : ", validationRuleResult.getValidationMessage());
            }
        }
    }

    @Test
    public void testPasswordErrorMsgForFourCharSeq() {
        List<ValidationRuleResult> validationRuleResultList = validationService.execute("sridsrid12");
        for (ValidationRuleResult validationRuleResult : validationRuleResultList) {
            if (SEQUENCE_RULE.equals(validationRuleResult.getValidationRuleName())) {
                Assert.assertEquals(SEQUENCE_RULE_MSG, validationRuleResult.getValidationMessage());
            }
        }
    }

}
