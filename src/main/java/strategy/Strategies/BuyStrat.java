package strategy.Strategies;

import strategy.ConvertStrategy;

public class BuyStrat implements ConvertStrategy {
    private static final double BUY_MARKUP = 1.02;

    @Override
    public double convert(double amount, double rate) {
        return amount * rate * BUY_MARKUP;
    }
}