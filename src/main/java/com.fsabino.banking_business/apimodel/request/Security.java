package com.fsabino.banking_business.apimodel.request;

import com.fasterxml.jackson.annotation.JsonProperty;

abstract class Security {

    @JsonProperty("client_id")
    long clientId;

    @JsonProperty
    int pin;

    public long getClientId() {
        return clientId;
    }

    public int getPin() {
        return pin;
    }
}
