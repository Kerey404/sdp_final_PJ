package strategy.Strategies;

import strategy.ConvertStrategy;

public class BuyStrat implements ConvertStrategy {

    @Override
    public double convert(double amount) {
        return amount * 0.98;
    }
}