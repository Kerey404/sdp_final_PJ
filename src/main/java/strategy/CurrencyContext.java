package strategy;

public class CurrencyContext {

    private ConvertStrategy strategy;

    public void setStrategy(ConvertStrategy strategy) {
        this.strategy = strategy;
    }

    public double performConvert(double amount, double rate) {
        return strategy.convert(amount, rate);
    }
}