package sk.po.bsc.exercise.paymenttracker.utils;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.unitils.reflectionassert.ReflectionComparatorMode;
import sk.po.bsc.exercise.paymenttracker.data.Payment;
import sk.po.bsc.exercise.paymenttracker.definitions.ECurrencyCode;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.unitils.reflectionassert.ReflectionAssert.assertReflectionEquals;

/**
 * @author Marek Krištof
 * Created: 22-Sep-19
 */
public class PaymentCalculatorTest {

    private List<Payment>                  payments           = new ArrayList<>();
    private Map<ECurrencyCode, BigDecimal> calculatedPayments = new HashMap<>();

    /**
     * If we want to convert from a double to a BigDecimal and have it round the way we expect use.
     */
    @Before
    public void setUp() {
        payments.add(new Payment(ECurrencyCode.EUR, BigDecimal.valueOf(100.00)));
        payments.add(new Payment(ECurrencyCode.USD, BigDecimal.valueOf(100)));
        payments.add(new Payment(ECurrencyCode.USD, BigDecimal.valueOf(-10)));
        payments.add(new Payment(ECurrencyCode.EUR, BigDecimal.valueOf(0.05)));
        payments.add(new Payment(ECurrencyCode.GBP, BigDecimal.valueOf(0.05)));
        payments.add(new Payment(ECurrencyCode.GBP, BigDecimal.valueOf(-0.55)));
        payments.add(new Payment(ECurrencyCode.HKD, BigDecimal.valueOf(500)));
        payments.add(new Payment(ECurrencyCode.HKD, BigDecimal.valueOf(-500)));


        calculatedPayments.put(ECurrencyCode.EUR, BigDecimal.valueOf(100.05));
        calculatedPayments.put(ECurrencyCode.GBP, BigDecimal.valueOf(-0.5));
        calculatedPayments.put(ECurrencyCode.USD, BigDecimal.valueOf(90));
        calculatedPayments.put(ECurrencyCode.HKD, BigDecimal.valueOf(0));
    }

    @Test
    public void doCalculation() {
        for (Map.Entry<ECurrencyCode, BigDecimal> entry : calculatedPayments.entrySet()) {
            assertReflectionEquals(entry.getValue(), PaymentCalculator.doCalculation(payments).get(entry.getKey()), ReflectionComparatorMode.LENIENT_ORDER);
        }
    }
}