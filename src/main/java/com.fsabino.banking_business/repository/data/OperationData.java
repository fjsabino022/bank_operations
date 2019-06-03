package com.fsabino.banking_business.repository.data;

import java.time.OffsetDateTime;
import java.util.UUID;

import static java.time.OffsetDateTime.now;
import static java.util.UUID.randomUUID;

public abstract class OperationData {

    public enum Type {
        DEPOSIT,
        TAKEOUT,
        TRANSFER;
    }

    private UUID operationId;
    private String cbu;
    private double amount;
    private OffsetDateTime datetime;
    private Type type;

    public UUID getOperationId() {
        return operationId;
    }

    public String getCbu() {
        return cbu;
    }

    public double getAmount() {
        return amount;
    }

    public OffsetDateTime getDatetime() {
        return datetime;
    }

    public Type getType() {
        return type;
    }

    OperationData(String cbu, double amount, Type type) {
        this.cbu = cbu;
        this.amount = amount;
        this.type = type;
        this.datetime = now();
        this.operationId = randomUUID();
    }

    @Override
    public String toString() {
        return "OperationData{" +
                "operationId=" + operationId +
                ", cbu='" + cbu + '\'' +
                ", amount=" + amount +
                ", datetime=" + datetime +
                ", type=" + type +
                '}';
    }
}
