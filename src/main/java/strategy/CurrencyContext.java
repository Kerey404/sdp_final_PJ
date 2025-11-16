package strategy;

public class CurrencyContext {

    private ConvertStrategy strategy;

    public void setStrategy(ConvertStrategy strategy) {
        this.strategy = strategy;
    }

    public double performConvert(double amount) {
        return strategy.convert(amount);
    }
}