package strategy.Strategies;

import strategy.ConvertStrategy;

public class GeneralStrat implements ConvertStrategy {

    @Override
    public double convert(double amount) {
        return amount;
    }
}
