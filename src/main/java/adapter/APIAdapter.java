package adapter;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import org.json.JSONObject;

public class APIAdapter {
    private String apiKey;
    private String baseCurrency;

    private Map<String, Double> fiatRates;
    private Map<String, Double> cryptoRates;

    // Предопределённый список криптовалют
    private final Set<String> cryptoSet = Set.of(
            "BTC", "ETH", "DOGE", "LTC", "BNB", "ADA", "XRP", "SOL", "DOT", "UNI"
    );

    public APIAdapter(String apiKey) {
        this.apiKey = apiKey;
        this.baseCurrency = "USD";
        this.fiatRates = new HashMap<>();
        this.cryptoRates = new HashMap<>();
    }

    public void setBaseCurrency(String currency) {
        this.baseCurrency = currency.toUpperCase();
    }

    public boolean fetchRates() {
        try {
            String urlStr = "https://api.unirateapi.com/api/rates?api_key="
                    + apiKey + "&from=" + baseCurrency;
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int status = conn.getResponseCode();
            if (status != 200) {
                System.out.println("Ошибка API: " + status);
                return false;
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            JSONObject json = new JSONObject(content.toString());
            JSONObject jsonRates = json.getJSONObject("rates");

            // Очищаем старые данные
            fiatRates.clear();
            cryptoRates.clear();

            Iterator<String> keys = jsonRates.keys();
            while (keys.hasNext()) {
                String key = keys.next().toUpperCase();
                double value = jsonRates.getDouble(key);

                if (cryptoSet.contains(key)) {
                    cryptoRates.put(key, value);
                } else {
                    fiatRates.put(key, value);
                }
            }

            return true;

        } catch (Exception e) {
            System.out.println("Ошибка при получении данных: " + e.getMessage());
            return false;
        }
    }


    public Double getFiatRate(String currency) {
        return fiatRates.get(currency.toUpperCase());
    }


    public Double getCryptoRate(String currency) {
        return cryptoRates.get(currency.toUpperCase());
    }

    public Double convertFiat(double amount, String targetCurrency) {
        Double rate = getFiatRate(targetCurrency);
        if (rate == null) return null;
        return amount * rate;
    }


    public Double convertCrypto(double amount, String targetCurrency) {
        Double rate = getCryptoRate(targetCurrency);
        if (rate == null) return null;
        return amount * rate;
    }

    // Геттеры для работы с коллекциями
    public Map<String, Double> getFiatRates() {
        return Collections.unmodifiableMap(fiatRates);
    }

    public Map<String, Double> getCryptoRates() {
        return Collections.unmodifiableMap(cryptoRates);
    }
}
