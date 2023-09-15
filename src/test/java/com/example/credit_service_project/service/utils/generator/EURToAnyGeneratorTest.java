package com.example.credit_service_project.service.utils.generator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EURToAnyGeneratorTest {

    @Test
    void generateCurrencyMap() throws IOException {
        Map<String, Double> currencyMap = new HashMap<>();
        String url = "https://www.ecb.europa.eu/stats/policy_and_exchange_rates/euro_reference_exchange_rates/html/index.en.html";

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
        currencyMap.put("EUR", 1.0);

            assertEquals(currencyMap, EURToAnyGenerator.generateCurrencyMap());
    }
}