package com.fsabino.banking_business.apimodel.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import static org.apache.commons.lang3.StringUtils.isBlank;

abstract class OperationRequest extends Security {

    @JsonProperty("account_number")
    String accountNumber;

    @JsonProperty
    double amount;

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException(String.format("amount %s cannot be minor than 0", amount));
        }
        this.amount = amount;
    }

    public void setAccountNumber(String accountNumber) {
        if (isBlank(accountNumber)) {
            throw new IllegalArgumentException("accountNumber cannot be null or empty");
        }
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "TakeOutRequest{" +
                "clientId=" + clientId +
                ", pin=" + pin +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                '}';
    }
}
