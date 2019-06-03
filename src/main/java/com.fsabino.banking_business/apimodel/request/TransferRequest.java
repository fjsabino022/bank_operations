package com.fsabino.banking_business.apimodel.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class TransferRequest extends OperationRequest {

    @JsonProperty
    private String cbu;

    public void setCbu(String cbu) {
        if (isBlank(cbu)) {
            throw new IllegalArgumentException("cbu cannot be null or empty");
        }
        this.cbu = cbu;
    }

    public String getCbu() {
        return cbu;
    }

    @Override
    public String toString() {
        return "TransferRequest{" +
                "clientId=" + clientId +
                ", pin=" + pin +
                ", accountNumber='" + accountNumber + '\'' +
                ", amount=" + amount +
                ", cbu='" + cbu + '\'' +
                '}';
    }
}
