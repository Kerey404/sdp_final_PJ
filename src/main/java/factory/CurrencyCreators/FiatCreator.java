package factory.CurrencyCreators;

import factory.CurrencyCreator;
import factory.CurrencyType;
import factory.CurrencyTypes.FiatCurrency;

public class FiatCreator extends CurrencyCreator {
    @Override
    public CurrencyType createCurrencyType() {
        return new FiatCurrency();
    }
}