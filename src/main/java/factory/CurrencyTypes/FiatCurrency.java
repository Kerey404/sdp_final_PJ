package factory.CurrencyTypes;

import factory.CurrencyType;

public class FiatCurrency implements CurrencyType {

    public String getIndex() {
        return "";
    }

    public int getBuyRate() {
        return 0;
    }

    public int getSellRate() {
        return 0;
    }

    public String getType() {
        return "";
    }
}