package com.example.credit_service_project.generator;

import com.example.credit_service_project.DTO.accountDTO.AddAccountDTORequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class AccountGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖÜẞabcdrfgilklmopqrstuvwxyzäöü0123456789";

    public String createRandomAccountNumber(Integer length) {
        StringBuilder stringBuilder = new StringBuilder(length);
        Random random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            stringBuilder.append(CHARACTERS.charAt(randomIndex));
        }
        return stringBuilder.toString();
    }

    public LocalDateTime createOpeningDay() {
        return LocalDateTime.now();
    }

    public LocalDateTime createClosingDate(Long yearsAmount) {
        return createOpeningDay().plusYears(yearsAmount);
    }

    public BigDecimal getUnpaidLoanDebt(AddAccountDTORequest request) {
        if (request.getLoanDebt().intValue() == 0 && request.getPercentageDebt().intValue() == 0){
            return new BigDecimal(0);
        }
        return null;
        // доработать
    }

    public BigDecimal getUnpaidPercentageLoanDebt(AddAccountDTORequest request) {
        if (request.getLoanDebt().intValue() == 0 && request.getPercentageDebt().intValue() == 0){
            return new BigDecimal(0);
        }
        return null;
        // доработать
    }



}
