import facade.CurrencyExchangeFacade;
import observer.ConsoleObserver;

import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        String apiKey = "ofeqLgVdXbQ2wWfmHi03cuca9Wty2SOZ7cHjMqZ24OrgpVW80PmWMFWd1Agcral7";
        CurrencyExchangeFacade facade = new CurrencyExchangeFacade(apiKey);
        facade.setObserver(new ConsoleObserver());
        Scanner scanner = new Scanner(System.in);

        if (!facade.refreshRates()) {
            System.out.println("Failed to fetch rates. Exiting...");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n--- Currency Exchange Menu ---");
            System.out.println("1. Set Base Currency");
            System.out.println("2. Check Currency Rates");
            System.out.println("3. Convert Fiat");
            System.out.println("4. Convert Crypto");
            System.out.println("5. Safe Convert");
            System.out.println("6. Set Strategy");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter base currency (default - USD): ");
                    String baseCurrency = scanner.nextLine().trim();
                    if (!baseCurrency.isEmpty()) {
                        facade.setBaseCurrency(baseCurrency);
                        facade.refreshRates();
                    }
                    break;

                case "2":
                    Set<String> fiatCurrencies = facade.getFiatCurrencies();
                    System.out.println("Available fiat currencies: " + fiatCurrencies);
                    Set<String> cryptoCurrencies = facade.getCryptoCurrencies();
                    System.out.println("Available crypto currencies: " + cryptoCurrencies);
                    break;

                case "3":
                    System.out.print("Enter amount: ");
                    double fiatAmount = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter target fiat currency: ");
                    String fiatTarget = scanner.nextLine().toUpperCase();
                    double fiatResult = facade.convertFiat(fiatAmount, fiatTarget);
                    System.out.println("Safe Result: " + fiatResult + " " + fiatTarget);
                    break;

                case "4":
                    System.out.print("Enter amount: ");
                    double cryptoAmount = Double.parseDouble(scanner.nextLine());
                    System.out.print("Enter target crypto currency: ");
                    String cryptoTarget = scanner.nextLine().toUpperCase();
                    double cryptoResult = facade.convertCrypto(cryptoAmount, cryptoTarget);
                    System.out.println("Result: " + cryptoResult + " " + cryptoTarget);
                    break;

                case "5":
                    boolean running1 = true;
                    while (running1) {
                        System.out.print("Safe Convert (1 - Fiat, 2 - Crypto, 0 - Go Back): ");
                        String safe_choice = scanner.nextLine();
                        switch (safe_choice) {
                            case "1":
                                System.out.print("Enter amount: ");
                                double safeFiatAmount = Double.parseDouble(scanner.nextLine());
                                System.out.print("Enter target fiat currency: ");
                                String safeFiatTarget = scanner.nextLine().toUpperCase();
                                double safeFiatResult = facade.safeConvertFiat(safeFiatAmount, safeFiatTarget);
                                System.out.println("Safe Result: " + safeFiatResult + " " + safeFiatTarget);
                                break;

                            case "2":
                                System.out.print("Enter amount: ");
                                double safeCryptoAmount = Double.parseDouble(scanner.nextLine());
                                System.out.print("Enter target crypto currency: ");
                                String safeCryptoTarget = scanner.nextLine().toUpperCase();
                                double safeCryptoResult = facade.safeConvertCrypto(safeCryptoAmount, safeCryptoTarget);
                                System.out.println("Result: " + safeCryptoResult + " " + safeCryptoTarget);
                                break;

                            case "0":
                                running1 = false;
                                System.out.println("Exiting...");
                                break;

                            default:
                                System.out.println("Invalid choice. Try again.");
                        }
                    }
                    break;

                case "6":
                    System.out.print("Set strategy for Fiat (BUY, SELL, NORMAL): ");
                    String fiatStrategy = scanner.nextLine().toUpperCase();
                    facade.setFiatStrategy(fiatStrategy);

                    System.out.print("Set strategy for Crypto (BUY, SELL, NORMAL): ");
                    String cryptoStrategy = scanner.nextLine().toUpperCase();
                    facade.setCryptoStrategy(cryptoStrategy);

                    System.out.println("Strategies updated!");
                    break;

                case "0":
                    running = false;
                    System.out.println("Exiting...");

                default:
                    System.out.println("Invalid choice. Try again.");
                    break;
            }
        }
        scanner.close();
    }
}