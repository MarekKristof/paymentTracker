package sk.po.bsc.exercise.paymenttracker.definitions;

/**
 * @author Marek Krištof
 */
public enum ECurrencyCode {
    EUR(1, "EUR", 1.10),
    USD(2, "USD", 1.00),
    GBP(3 ,"GBP", 1.24),
    RMB(4 ,"RMB", 0.14),
    HKD(5 ,"HKD", 0.12);

    private final int    index;
    private final String name;
    private final double exchangeRate;

   ECurrencyCode(int index, String name, double exchangeRate) {
        this.index = index;
        this.name = name;
        this.exchangeRate = exchangeRate;
    }

    public int getIndex() {
        return index;
    }

    public  String getName() {
        return name;
    }

    public double getExchangeRate() {
        return exchangeRate;
    }
}
