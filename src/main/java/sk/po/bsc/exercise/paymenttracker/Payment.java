package sk.po.bsc.exercise.paymenttracker;

import java.math.BigDecimal;

/**
 * @author Marek Krištof
 */
public class Payment {

    private ECurrencyCode currency;
    private BigDecimal    amount;

    public Payment(ECurrencyCode currency, BigDecimal amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public ECurrencyCode getCurrency() {
        return currency;
    }

    public void setCurrency(ECurrencyCode currency) {
        this.currency = currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "currency=" + currency +
                ", amount=" + amount +
                '}';
    }
}
