package facade;

import adapter.APIAdapter;
import decorator.*;
import observer.*;

public class CurrencyExchangeFacade {
    private APIAdapter adapter;
    private ObservableAdapter observable;
    private Observer observer;

    public CurrencyExchangeFacade(String apiKey) {
        adapter = new APIAdapter(apiKey);
        observable = new ObservableAdapter(adapter);
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
        observable.setObserver(observer);
    }

    public boolean fetchRates() {
        return adapter.fetchRates();
    }

    public Double convertFiat(double amount, String targetCurrency) {
        Conversion fiat = new FiatConversion(adapter);
        Conversion decorated = new ConversionDecorator(fiat);
        Double result = decorated.convert(amount, targetCurrency);
        observable.convertFiat(amount, targetCurrency); // уведомление
        return result;
    }

    public Double convertCrypto(double amount, String targetCurrency) {
        Conversion crypto = new CryptoConversion(adapter);
        Conversion decorated = new ConversionDecorator(crypto);
        Double result = decorated.convert(amount, targetCurrency);
        observable.convertCrypto(amount, targetCurrency); // уведомление
        return result;
    }
}
