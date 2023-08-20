package com.example.credit_service_project.service.utils.generator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class USDToAnyCurrency {

    static Map<String, String> currency = CountryToCurrency.parseCountriesAndCurrencies();

    public static Map<String, Double> parseExchangeRates() {
        Map<String, Double> exchangeRates = new HashMap<>();
        String url = "https://www.theglobaleconomy.com/rankings/Dollar_exchange_rate/"; // Замените на URL вашей таблицы

        try {
            Document document = Jsoup.connect(url).get();
            Element table = document.select("table#benchmarkTable").first();
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements cols = row.select("td");

                String country = cols.get(0).select("a").text();
                String exchangeRateStr = cols.get(1).text();
                double exchangeRate = parseExchangeRate(exchangeRateStr);

                for (String e : currency.keySet()) {
                    if (e.equalsIgnoreCase(country)) {
                        String value = currency.get(e);
                        exchangeRates.put(value, exchangeRate);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exchangeRates;
    }

    private static double parseExchangeRate(String exchangeRateStr) {
        try {
            return Double.parseDouble(exchangeRateStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
