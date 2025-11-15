package strategy.Strategies;

import strategy.ConvertStrategy;

public class SellStrat implements ConvertStrategy {
    private static final double SELL_MARKUP = 0.98;

    @Override
    public double convert(double amount, double rate) {
        return amount * rate * SELL_MARKUP;
    }
}
