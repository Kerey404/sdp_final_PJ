package factory.FactoryCreators;

import factory.StrategyCreator;
import strategy.ConvertStrategy;
import strategy.Strategies.GeneralStrat;

public class GeneralStratCreator extends StrategyCreator {
    @Override
    public ConvertStrategy createStrategy() {
        return new GeneralStrat();
    }
}
