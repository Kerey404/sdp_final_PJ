package factory;

import factory.FactoryCreators.BuyStratCreator;
import factory.FactoryCreators.GeneralStratCreator;
import factory.FactoryCreators.SellStratCreator;
import strategy.ConvertStrategy;

public abstract class StrategyCreator {
    public abstract ConvertStrategy createStrategy();
    public static StrategyCreator getCreator(String type) {
        return switch (type.toUpperCase()) {
            case "BUY" -> new BuyStratCreator();
            case "SELL" -> new SellStratCreator();
            case "NORMAL" -> new GeneralStratCreator();
            default -> throw new IllegalArgumentException("Unknown strategy type: " + type);
        };
    }
}