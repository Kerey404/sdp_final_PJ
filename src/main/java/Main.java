import facade.CurrencyExchangeFacade;
import observer.ConsoleObserver;

public class Main {
    public static void main(String[] args) {
        String apiKey = "ofeqLgVdXbQ2wWfmHi03cuca9Wty2SOZ7cHjMqZ24OrgpVW80PmWMFWd1Agcral7";
        CurrencyExchangeFacade facade = new CurrencyExchangeFacade(apiKey);
        facade.setObserver(new ConsoleObserver());

        if (facade.fetchRates()) {
            facade.convertFiat(100, "EUR");
            facade.convertCrypto(0.5, "BTC");
        }
    }
}
