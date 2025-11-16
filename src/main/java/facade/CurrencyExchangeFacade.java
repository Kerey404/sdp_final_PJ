package facade;

import adapter.APIAdapter;
import decorator.*;
import observer.*;
import strategy.ConvertStrategy;

import java.util.Set;

public class CurrencyExchangeFacade {
    private APIAdapter adapter;
    private ObservableAdapter observable;
    private Observer observer;
    private ConvertStrategy fiatStrategy;
    private ConvertStrategy cryptoStrategy;

    public CurrencyExchangeFacade(String apiKey) {
        adapter = new APIAdapter(apiKey);
        observable = new ObservableAdapter(adapter);
    }

    public void setBaseCurrency(String currency) {
        adapter.setBaseCurrency(currency);
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
        observable.setObserver(observer);
    }

    public boolean refreshRates() {
        boolean refreshed = adapter.fetchRates();
        if (refreshed && observer != null) observer.update("Refreshed Rates");
        return refreshed;
    }

    public Set<String> getFiatCurrencies() {
        return adapter.getFiatRates().keySet();
    }

    public Set<String> getCryptoCurrencies() {
        return adapter.getCryptoRates().keySet();
    }

    public void setFiatStrategy(String type) {
        this.fiatStrategy = factory.StrategyCreator.getCreator(type).createStrategy();
    }

    public void setCryptoStrategy(String type) {
        this.cryptoStrategy = factory.StrategyCreator.getCreator(type).createStrategy();
    }

    public Double convertFiat(double amount, String targetCurrency) {

        Conversion fiat = new FiatConversion(adapter);
        Conversion decorated = new ConversionDecorator(fiat);

        Double result = decorated.convert(amount, targetCurrency);

        if (fiatStrategy != null) {
            result = fiatStrategy.convert(result);
        }

        observable.convertFiat(amount, targetCurrency);

        return result;
    }

    public Double convertCrypto(double amount, String targetCurrency) {

        Conversion crypto = new CryptoConversion(adapter);
        Conversion decorated = new ConversionDecorator(crypto);

        Double result = decorated.convert(amount, targetCurrency);

        if (cryptoStrategy != null) {
            result = cryptoStrategy.convert(result);
        }

        observable.convertCrypto(amount, targetCurrency);

        return result;
    }

    public Double safeConvertFiat(double amount, String targetCurrency) {
        try {
            if (fiatStrategy == null) {
                System.out.println("SafeConvertFiat Strategy not set! Using NORMAL strategy by default.");
                setFiatStrategy("NORMAL");
            }
            return convertFiat(amount, targetCurrency);
        } catch (Exception e) {
            System.out.println("Fiat conversion failed: " + e.getMessage());
            return null;
        }
    }

    public Double safeConvertCrypto(double amount, String targetCurrency) {
        try {
            if (cryptoStrategy == null) {
                System.out.println("SafeConvertCrypto Strategy not set! Using NORMAL strategy by default.");
                setCryptoStrategy("NORMAL");
            }
            return convertCrypto(amount, targetCurrency);
        } catch (Exception e) {
            System.out.println("Crypto conversion failed: " + e.getMessage());
            return null;
        }
    }
}