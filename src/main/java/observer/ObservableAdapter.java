package observer;

import adapter.APIAdapter;

public class ObservableAdapter {
    private APIAdapter adapter;
    private Observer observer;

    public ObservableAdapter(APIAdapter adapter) {
        this.adapter = adapter;
    }

    public void setObserver(Observer observer) {
        this.observer = observer;
    }

    public Double convertFiat(double amount, String targetCurrency) {
        Double result = adapter.convertFiat(amount, targetCurrency);
        if (observer != null) {
            observer.update("Фиат: " + amount + " → " + targetCurrency + " = " + result);
        }
        return result;
    }

    public Double convertCrypto(double amount, String targetCurrency) {
        Double result = adapter.convertCrypto(amount, targetCurrency);
        if (observer != null) {
            observer.update("Крипта: " + amount + " → " + targetCurrency + " = " + result);
        }
        return result;
    }
}
