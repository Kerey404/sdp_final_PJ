package decorator;

import adapter.APIAdapter;

public class CryptoConversion implements Conversion {
    private APIAdapter adapter;

    public CryptoConversion(APIAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public Double convert(double amount, String targetCurrency) {
        return adapter.convertCrypto(amount, targetCurrency);
    }
}
