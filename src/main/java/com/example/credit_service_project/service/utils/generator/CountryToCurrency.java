package com.example.credit_service_project.service.utils.generator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CountryToCurrency {
    public static Map<String, String> parseCountriesAndCurrencies() {
        Map<String, String> countriesAndCurrencies = new HashMap<>();
        String url = "https://www.iban.com/currency-codes"; // Замените на URL вашей таблицы

        try {
            Document document = Jsoup.connect(url).get();
            System.out.println(document);
            Element table = document.select("table.table-bordered").first();
            Elements rows = table.select("tbody tr");

            for (Element row : rows) {
                Elements cols = row.select("td");

                String countryName = cols.get(0).text();
                String currencyCode = cols.get(2).text();

                countriesAndCurrencies.put(countryName, currencyCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return countriesAndCurrencies;
    }
}
