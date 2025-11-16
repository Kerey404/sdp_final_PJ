package factory;

import strategy.ConvertStrategy;

public abstract class StrategyCreator {
    public abstract ConvertStrategy createStrategy();
}
