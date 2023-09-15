package com.example.credit_service_project.service.utils.generator;

import com.example.credit_service_project.validation.ErrorsMessage;
import com.example.credit_service_project.validation.exceptions.NotFoundException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class CreditGenerator {

    private final static Map<String, Double> rateBaseMap = new HashMap<>();

    public static BigDecimal getInterestRateByCountry(String country) {

        String url = "https://www.economy.com/indicators/lending-rate";
        try {
            Document document = Jsoup.connect(url).get();

            Element table = document.select("div.tab-pane.active table.table").first();
            assert table != null;
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

        if (!rateBaseMap.containsKey(country)){
            throw new NotFoundException(ErrorsMessage.UNKNOWN_COUNTRY_MESSAGE + " Country: " + country);
        }
        return BigDecimal.valueOf(rateBaseMap.get(country));
    }

}
