package sk.po.bsc.exercise.paymenttracker.definitions;

/**
 * @author Marek Krištof
 */
public enum ECurrencyCode {
    EUR(1, "EUR", 1.29),
    USD(2, "USD", 1.00),
    RMB(3 ,"RMB", 0.83),
    HKD(3 ,"HKD", 0.82);

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
