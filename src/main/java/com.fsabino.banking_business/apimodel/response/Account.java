package com.fsabino.banking_business.apimodel.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Account {

    private String accountNumber;
    private String cbu;
    private double price;
    private String currency;
    private long clientId;

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCbu() {
        return cbu;
    }

    public double getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public long getClientId() {
        return clientId;
    }

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    private Account(String accountNumber, String cbu, double price, String currency, long clientId) {
        this.accountNumber = accountNumber;
        this.cbu = cbu;
        this.price = price;
        this.currency = currency;
        this.clientId = clientId;
    }

    public static class AccountBuilder {
        @JsonProperty("account_number")
        private String accountNumber;
        @JsonProperty
        private String cbu;
        @JsonProperty
        private double price;
        @JsonProperty
        private String currency;
        @JsonProperty("client_id")
        private long clientId;

        public AccountBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public AccountBuilder cbu(String cbu) {
            this.cbu = cbu;
            return this;
        }

        public AccountBuilder price(double price) {
            this.price = price;
            return this;
        }

        public AccountBuilder currency(String currency) {
            this.currency = currency;
            return this;
        }

        public AccountBuilder clientId(long clientId) {
            this.clientId = clientId;
            return this;
        }

        public Account build() {
            return new Account(this.accountNumber,
                    this.cbu,
                    this.price,
                    this.currency,
                    this.clientId);
        }
    }
}