package com.fsabino.banking_business.repository.data;

public class AccountData {

    public enum Currency {
        PESO("$"),
        EURO("€"),
        DOLAR("USD");

        private String name;

        Currency(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }
    }

    private String accountNumber;
    private String cbu;
    private Double price;
    private Currency currency;
    private long clientId;

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCbu() {
        return cbu;
    }

    public Double getPrice() {
        return price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public long getClientId() {
        return clientId;
    }

    public void addMoney(double amount) {
        price+= amount;
    }

    public void subtractMoney(double amount) {
        price+= -amount;
    }

    @Override
    public String toString() {
        return "AccountData{" +
                "accountNumber='" + accountNumber + '\'' +
                ", cbu='" + cbu + '\'' +
                ", price=" + price +
                ", currency=" + currency +
                ", clientId=" + clientId +
                '}';
    }

    private AccountData(String accountNumber,
                        String cbu,
                        Double price,
                        Currency currency,
                        long clientId) {
        this.accountNumber = accountNumber;
        this.cbu = cbu;
        this.price = price;
        this.currency = currency;
        this.clientId = clientId;
    }

    public static AccountBuilder builder() {
        return new AccountBuilder();
    }

    public static class AccountBuilder {
        private String accountNumber;
        private String cbu;
        private Double price;
        private Currency currency;
        private long clientId;

        public AccountBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public AccountBuilder cbu(String cbu) {
            this.cbu = cbu;
            return this;
        }

        public AccountBuilder price(Double price) {
            this.price = price;
            return this;
        }

        public AccountBuilder currency(Currency currency) {
            this.currency = currency;
            return this;
        }

        public AccountBuilder clientId(long clientId) {
            this.clientId = clientId;
            return this;
        }

        public AccountData build() {
            return new AccountData(this.accountNumber,
                    this.cbu,
                    this.price,
                    this.currency,
                    this.clientId);
        }
    }
}
