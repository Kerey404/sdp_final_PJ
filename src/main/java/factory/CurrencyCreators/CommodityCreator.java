package factory.CurrencyCreators;

import factory.CurrencyCreator;
import factory.CurrencyType;
import factory.CurrencyTypes.CommodityCurrency;

public class CommodityCreator extends CurrencyCreator {
    @Override
    public CurrencyType createCurrencyType() {
        return new CommodityCurrency();
    }
}