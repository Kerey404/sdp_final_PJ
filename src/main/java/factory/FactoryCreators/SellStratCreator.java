package factory.FactoryCreators;

import factory.StrategyCreator;
import strategy.ConvertStrategy;
import strategy.Strategies.SellStrat;

public class SellStratCreator extends StrategyCreator {
    @Override
    public ConvertStrategy createStrategy() {
        return new SellStrat();
    }
}
