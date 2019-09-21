package sk.po.bsc.exercise.paymenttracker;

import sk.po.bsc.exercise.paymenttracker.definitions.ECurrencyCode;
import sk.po.bsc.exercise.paymenttracker.utils.PaymentCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Observer;

/**
 * @author Marek Krištof
 */
public class CommandReader implements Observer {

    private PaymentCalculator     paymentCalculator     = PaymentCalculator.INSTANCE;
    private PaymentListFileReader paymentListFileReader = PaymentListFileReader.INSTANCE;

    @Override
    public void update(java.util.Observable o, Object arg) {
        String command = (String)arg;

        System.out.println("CommandReader got new command:" + (String) arg);

        if (command.equals("TIME")) {
            System.out.println(paymentListFileReader.readFileWithPayments().toString());
            for (Map.Entry<ECurrencyCode, BigDecimal> groupedPayment :
                    paymentCalculator.doCalculation(paymentListFileReader.readFileWithPayments()).entrySet()) {
                System.out.println(groupedPayment + "  "
                        + (groupedPayment.getValue()
                        .multiply(new BigDecimal(groupedPayment.getKey().getExchangeRate())).setScale(2, RoundingMode.HALF_UP)));
            }
        }
    }

}
