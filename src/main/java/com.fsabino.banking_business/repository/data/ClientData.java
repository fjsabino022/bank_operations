package com.fsabino.banking_business.repository.data;

import com.google.common.base.Objects;

public class ClientData {

    public enum Type {
        GOLD,
        SELECT,
        BLACK;
    }

    private long clientId;
    private String fullName;
    private Type type;
    private int pin;

    public long getClientId() {
        return clientId;
    }

    public String getFullName() {
        return fullName;
    }

    public Type getType() {
        return type;
    }

    public int getPin() {
        return pin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClientData clientData = (ClientData) o;
        return clientId == clientData.clientId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(clientId);
    }

    @Override
    public String toString() {
        return "ClientData{" +
                "clientId=" + clientId +
                ", fullName='" + fullName + '\'' +
                ", type=" + type +
                ", pin=" + pin +
                '}';
    }

    private ClientData(long clientId, String fullName, Type type, int pin) {
        this.fullName = fullName;
        this.type = type;
        this.pin = pin;
        this.clientId = clientId;
    }

    public static ClientBuilder builder() {
        return new ClientBuilder();
    }

    public static class ClientBuilder {
        private long clientId;
        private String fullName;
        private Type type;
        private int pin;

        public ClientBuilder clientId(long clientId) {
            this.clientId = clientId;
            return this;
        }

        public ClientBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public ClientBuilder type(Type type) {
            this.type = type;
            return this;
        }

        public ClientBuilder pin(int pin) {
            this.pin = pin;
            return this;
        }

        public ClientData build() {
            return new ClientData(this.clientId, this.fullName, this.type, this.pin);
        }
    }
}
