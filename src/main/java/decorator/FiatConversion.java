package decorator;

import adapter.APIAdapter;

public class FiatConversion implements Conversion {
    private APIAdapter adapter;

    public FiatConversion(APIAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public Double convert(double amount, String targetCurrency) {
        return adapter.convertFiat(amount, targetCurrency);
    }
}
