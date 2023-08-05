package com.example.credit_service_project.service.errors;

public class ErrorsMessage {

    public static final String NOT_FOUND_ACCOUNT_MESSAGE = "NO SUCH ACCOUNT WAS FOUND!";

    public static final String NOT_FOUND_CARD_MESSAGE = "NO SUCH CARD WAS FOUND!";
    public static final String NEGATIVE_BALANCE_EXCEPTION = "OPERATION SUM IS GREATER THAN BALANCE, PLEASE TOP UP YOUR ACCOUNT!";
    public static final String NOT_FOUND_OPERATION_MESSAGE = "NO SUCH OPERATION WAS FOUND!";
    public static final String OPERATION_TYPE_CHANGE_MESSAGE = "CHANGE OPERATION CREDIT TO DEBIT IS IMPOSSIBLE!";
    public static final String NOT_FOUND_CLIENT_MESSAGE = "NO SUCH CLIENT WAS FOUND!";
    public static final String NOT_FOUND_MANAGER_MESSAGE = "NO SUCH MANAGER WAS FOUND!";


    public static final String UNABLE_TO_ADD_ACCOUNT_MESSAGE = "THIS CLIENT WAS NOT FOUND, CREATING AN ACCOUNT IS IMPOSSIBLE!";
    public static final String UNABLE_TO_ADD_CARD_MESSAGE = "THIS ACCOUNT WAS NOT CREATED, ADDING A CARD IS IMPOSSIBLE!";
    public static final String UNABLE_TO_ADD_OPERATION_MESSAGE = "THIS ACCOUNT WAS NOT CREATED, CREATING AN OPERATION IS IMPOSSIBLE!";

}
