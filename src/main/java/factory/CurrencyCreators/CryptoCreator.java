package factory.CurrencyCreators;

import factory.CurrencyCreator;
import factory.CurrencyType;
import factory.CurrencyTypes.CryptoCurrency;

public class CryptoCreator extends CurrencyCreator {
    @Override
    public CurrencyType createCurrencyType() {
        return new CryptoCurrency();
    }
}