package com.fsabino.banking_business.apimodel.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Operation {

    private UUID operationId;
    private String cbu;
    private double amount;
    private String datetime;
    private String type;

    public UUID getOperationId() {
        return operationId;
    }

    public String getCbu() {
        return cbu;
    }

    public double getAmount() {
        return amount;
    }

    public String getDatetime() {
        return datetime;
    }

    public String getType() {
        return type;
    }

    private Operation(UUID operationId, String cbu, double amount, String datetime, String type) {
        this.operationId = operationId;
        this.cbu = cbu;
        this.amount = amount;
        this.datetime = datetime;
        this.type = type;
    }

    public static OperationBuilder builder() {
        return new OperationBuilder();
    }

    public static class OperationBuilder {
        @JsonProperty("operation_id")
        private UUID operationId;

        @JsonProperty
        private String cbu;

        @JsonProperty
        private double amount;

        @JsonProperty("date_time")
        private String datetime;

        @JsonProperty
        private String type;

        public OperationBuilder operationId(UUID operationId) {
            this.operationId = operationId;
            return this;
        }

        public OperationBuilder cbu(String cbu) {
            this.cbu = cbu;
            return this;
        }

        public OperationBuilder amount(double amount) {
            this.amount = amount;
            return this;
        }

        public OperationBuilder dateTime(String dateTime) {
            this.datetime = dateTime;
            return this;
        }

        public OperationBuilder type(String type) {
            this.type = type;
            return this;
        }

        public Operation build() {
            return new Operation(this.operationId,
                    this.cbu,
                    this.amount,
                    this.datetime,
                    this.type);
        }
    }
}
