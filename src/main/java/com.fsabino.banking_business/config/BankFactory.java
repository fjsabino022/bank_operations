package com.fsabino.banking_business.config;

import com.fsabino.banking_business.controller.AccountController;
import com.fsabino.banking_business.controller.OperationController;
import com.fsabino.banking_business.controller.PingController;
import com.fsabino.banking_business.converter.AccountDataToAccountConverter;
import com.fsabino.banking_business.converter.OperationDataToOperationConverter;
import com.fsabino.banking_business.repository.*;
import com.fsabino.banking_business.service.AccountService;
import com.fsabino.banking_business.service.ClientService;
import com.fsabino.banking_business.service.OperationService;

class BankFactory {

    // converter
    private static OperationDataToOperationConverter OPERATION_DATA_TO_OPERATION_CONVERTER = null;
    private static AccountDataToAccountConverter ACCOUNT_DATA_TO_ACCOUNT_CONVERTER = null;

    // repository
    private static ClientRepository CLIENT_REPOSITORY = null;
    private static AccountRepository ACCOUNT_REPOSITORY = null;
    private static OperationRepository OPERATION_REPOSITORY = null;

    // service
    private static ClientService CLIENT_SERVICE = null;
    private static AccountService ACCOUNT_SERVICE = null;
    private static OperationService OPERATION_SERVICE = null;

    // controller
    private static AccountController ACCOUNT_CONTROLLER = null;
    private static OperationController OPERATION_CONTROLLER = null;
    private static PingController PING_CONTROLLER = null;

    private static OperationDataToOperationConverter getOperationDataToOperationConverter() {
        if (OPERATION_DATA_TO_OPERATION_CONVERTER == null) {
            OPERATION_DATA_TO_OPERATION_CONVERTER = new OperationDataToOperationConverter();
        }
        return OPERATION_DATA_TO_OPERATION_CONVERTER;
    }

    private static AccountDataToAccountConverter getAccountDataToAccountConverter() {
        if (ACCOUNT_DATA_TO_ACCOUNT_CONVERTER == null) {
            ACCOUNT_DATA_TO_ACCOUNT_CONVERTER = new AccountDataToAccountConverter();
        }
        return ACCOUNT_DATA_TO_ACCOUNT_CONVERTER;
    }

    private static ClientRepository getClientRepository() {
        if (CLIENT_REPOSITORY == null) {
            CLIENT_REPOSITORY = new ClientRepositoryImpl();
        }
        return CLIENT_REPOSITORY;
    }

    private static AccountRepository getAccountRepository() {
        if (ACCOUNT_REPOSITORY == null) {
            ACCOUNT_REPOSITORY = new AccountRepositoryImpl();
        }
        return ACCOUNT_REPOSITORY;
    }

    private static OperationRepository getOperationRepository() {
        if (OPERATION_REPOSITORY == null) {
            OPERATION_REPOSITORY = new OperationRepositoryImpl();
        }
        return OPERATION_REPOSITORY;
    }

    private static ClientService getClientService() {
        if (CLIENT_SERVICE == null) {
            CLIENT_SERVICE = new ClientService(getClientRepository());
        }
        return CLIENT_SERVICE;
    }

    private static AccountService getAccountService() {
        if (ACCOUNT_SERVICE == null) {
            ACCOUNT_SERVICE = new AccountService(getAccountRepository(),
                    getClientService(),
                    getAccountDataToAccountConverter());
        }
        return ACCOUNT_SERVICE;
    }

    private static OperationService getOperationService() {
        if (OPERATION_SERVICE == null) {
            OPERATION_SERVICE = new OperationService(getClientService(),
                    getOperationRepository(),
                    getAccountService(),
                    getOperationDataToOperationConverter());
        }
        return OPERATION_SERVICE;
    }

    static AccountController getAccountController() {
        if (ACCOUNT_CONTROLLER == null) {
            ACCOUNT_CONTROLLER = new AccountController(getAccountService());
        }
        return ACCOUNT_CONTROLLER;
    }

    static OperationController getOperationController() {
        if (OPERATION_CONTROLLER == null) {
            OPERATION_CONTROLLER = new OperationController(getOperationService());
        }
        return OPERATION_CONTROLLER;
    }

    static PingController getPingController() {
        if (PING_CONTROLLER == null) {
            PING_CONTROLLER = new PingController();
        }
        return PING_CONTROLLER;
    }
}
