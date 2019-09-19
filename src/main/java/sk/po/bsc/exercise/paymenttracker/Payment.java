package sk.po.bsc.exercise.paymenttracker;

/**
 * @author Marek Krištof
 */
public class Payment {

    private ECurrencyCode currency;
    private long amount;

    public Payment(ECurrencyCode currency, long amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public ECurrencyCode getCurrency() {
        return currency;
    }

    public void setCurrency(ECurrencyCode currency) {
        this.currency = currency;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }
}
