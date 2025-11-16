package decorator;

public class ConversionDecorator implements Conversion {
    protected Conversion wrapped;

    public ConversionDecorator(Conversion wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public Double convert(double amount, String targetCurrency) {
        Double result = wrapped.convert(amount, targetCurrency);

        // округление до 4 знаков
        if (result != null) {
            result = Math.round(result * 10000.0) / 10000.0;
        }

        System.out.println("[Decorator] Конвертация выполнена: " + amount + " → " + result + " " + targetCurrency);
        return result;
    }
}
