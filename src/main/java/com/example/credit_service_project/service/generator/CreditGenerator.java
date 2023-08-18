package com.example.credit_service_project.service.generator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import static java.math.RoundingMode.HALF_UP;

public class CreditGenerator {

    private final static Map<String, Double> rateBaseMap = new HashMap<>();

    public static BigDecimal getRateBaseByCountry(String country) {
        String url = "https://www.economy.com/indicators/lending-rate";
        try {
            Document document = Jsoup.connect(url).get();

            Element table = document.select("div.tab-pane.active table.table").first();
            Elements rows = table.select("tr");

            for (int i = 1; i < rows.size(); i++) {
                Element row = rows.get(i);
                Elements cols = row.select("td");

                String passedCountry = cols.get(0).select("a").text();
                double rate = Double.parseDouble(cols.get(2).select("span.pull-right").text());

                rateBaseMap.put(passedCountry, rate);
            }
        } catch (IOException e) {
            throw new RuntimeException();
        }
        return BigDecimal.valueOf(rateBaseMap.get(country));
    }


    public static BigDecimal calculateInterestRate(BigDecimal sum, BigDecimal percents, Integer mounts) {
        BigDecimal hundred = new BigDecimal("100");
        int years = mounts / 12;
        return sum.multiply((percents.divide(hundred,2, HALF_UP))).multiply(BigDecimal.valueOf(years));
    }
}
