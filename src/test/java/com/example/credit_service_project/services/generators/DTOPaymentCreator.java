//package com.example.credit_service_project.services.generators;
//
//import com.example.credit_service_project.DTO.paymentDTO.AddPaymentRequestDTO;
//import com.example.credit_service_project.DTO.paymentDTO.AddPaymentScheduleDTOResponse;
//import com.example.credit_service_project.DTO.paymentDTO.PaymentResponseDTO;
//import com.example.credit_service_project.DTO.paymentDTO.PaymentsBelongsToAccountRequest;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.util.UUID;
//
//import static java.time.Month.AUGUST;
//
//public class DTOPaymentCreator {
//
//    public static AddPaymentRequestDTO getAddPaymentRequest() {
//        return new AddPaymentRequestDTO(
//                UUID.fromString("00009999-2222-1111-a456-426655440000"),
//                "A10B3U3OI9",
//                LocalDate.of(2023, AUGUST, 30),
//                new BigDecimal("300"),
//                new BigDecimal("57")
//        );
//    }
//
//    public static AddPaymentRequestDTO getAddPaymentRequestWithErrors() {
//        return new AddPaymentRequestDTO(
//                UUID.fromString("00009999-2222-1111-a456-426655440000"),
//                "A10B3U3OI9",
//                LocalDate.of(2020, AUGUST, 30),
//                new BigDecimal("300"),
//                null
//        );
//    }
//
//    public static AddPaymentScheduleDTOResponse getAddPaymentScheduleDTOResponse() {
//        return new AddPaymentScheduleDTOResponse(
//                "A10B3U3OI9",
//                LocalDate.of(2023, AUGUST, 30),
//                new BigDecimal("300"),
//                new BigDecimal("57")
//        );
//    }
//
//    public static PaymentsBelongsToAccountRequest getPaymentsBelongsToAccountRequest() {
//        return new PaymentsBelongsToAccountRequest(
//                UUID.fromString("00009999-2222-1111-a456-426655440000"),
//                "A10B3U3OI9"
//        );
//    }
//
//    public static PaymentResponseDTO getPaymentResponseDTO() {
//        return new PaymentResponseDTO(
//                UUID.fromString("00009999-2222-1111-a456-426655440000"),
//                LocalDate.of(2023, AUGUST, 30),
//                null,
//                new BigDecimal("0"),
//                new BigDecimal("300"),
//                new BigDecimal("57"),
//                false
//        );
//    }
//}
