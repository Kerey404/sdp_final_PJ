package factory.FactoryCreators;

import factory.StrategyCreator;
import strategy.ConvertStrategy;
import strategy.Strategies.BuyStrat;

public class BuyStratCreator extends StrategyCreator {
    @Override
    public ConvertStrategy createStrategy() {
        return new BuyStrat();
    }
}
