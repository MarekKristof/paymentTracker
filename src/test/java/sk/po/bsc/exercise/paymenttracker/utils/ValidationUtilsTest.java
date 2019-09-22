package sk.po.bsc.exercise.paymenttracker.utils;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Marek Krištof
 */
public class ValidationUtilsTest {

    @Test
    public void isPaymentValid() {
        assertTrue(ValidationUtils.isPaymentValid("USD 100"));
        assertTrue(ValidationUtils.isPaymentValid("USD 100.0"));
        assertTrue(ValidationUtils.isPaymentValid("HKD -100.00"));
        assertTrue(ValidationUtils.isPaymentValid("EUR 100.00"));

        assertFalse(ValidationUtils.isPaymentValid("HKD -100.000"));
        assertFalse(ValidationUtils.isPaymentValid("HKO -100.000"));
        assertFalse(ValidationUtils.isPaymentValid("USA 100.1654"));
        assertFalse(ValidationUtils.isPaymentValid("usa 100.1654"));
        assertFalse(ValidationUtils.isPaymentValid("usd 0100.1654"));
        assertFalse(ValidationUtils.isPaymentValid("hkd -0.100.1654"));
    }

    @Test
    public void isCurrencyKnown() {
        assertTrue(ValidationUtils.isCurrencyKnown("USD"));
        assertTrue(ValidationUtils.isCurrencyKnown("HKD"));
        assertTrue(ValidationUtils.isCurrencyKnown("EUR"));
        assertTrue(ValidationUtils.isCurrencyKnown("RMB"));
        assertTrue(ValidationUtils.isCurrencyKnown("GBP"));

        assertFalse(ValidationUtils.isPaymentValid("HKDA"));
        assertFalse(ValidationUtils.isPaymentValid("HK"));
        assertFalse(ValidationUtils.isPaymentValid("H"));
        assertFalse(ValidationUtils.isPaymentValid("hkd"));
        assertFalse(ValidationUtils.isPaymentValid("usd"));
        assertFalse(ValidationUtils.isPaymentValid("USA"));
    }

    @Test
    public void isCommandKnown() {
        assertTrue(ValidationUtils.isCommandKnown("TIME"));
        assertTrue(ValidationUtils.isCommandKnown("QUIT"));
        assertTrue(ValidationUtils.isCommandKnown("PRINTOUT"));

        assertFalse(ValidationUtils.isCommandKnown("time"));
        assertFalse(ValidationUtils.isCommandKnown("quit"));
        assertFalse(ValidationUtils.isCommandKnown("exit"));
        assertFalse(ValidationUtils.isCommandKnown("PRINT"));
        assertFalse(ValidationUtils.isCommandKnown("ADD"));
        assertFalse(ValidationUtils.isCommandKnown("HELP"));
    }
}