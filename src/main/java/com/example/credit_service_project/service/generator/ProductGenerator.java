package com.example.credit_service_project.service.generator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ProductGenerator {

    public static Map<String, Double> generatCurrencyMap() {
        Map<String, Double> currencyMap = new HashMap<>();
        String url = "https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html";

        try {
            Document document = Jsoup.connect(url).get();

            Elements rows = document.select("tbody tr");
            for (Element row : rows) {
                Elements columns = row.select("td");
                if (columns.size() >= 3) {
                    String currencyCode = columns.get(0).text();
                    double exchangeRate = Double.parseDouble(columns.get(2).text().replace(",", "."));
                    currencyMap.put(currencyCode, exchangeRate);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return currencyMap;
    }
}
