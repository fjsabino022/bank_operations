package com.fsabino.banking_business.apimodel.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class AccountRequest extends Security {

    @JsonProperty("account_number")
    private String accountNumber;

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        if (isBlank(accountNumber)) {
            throw new IllegalArgumentException("accountNumber cannot be null or empty");
        }
        this.accountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "AccountRequest{" +
                "clientId=" + clientId +
                ", pin=" + pin +
                ", accountNumber='" + accountNumber + '\'' +
                '}';
    }
}
