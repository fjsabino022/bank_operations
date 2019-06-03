package com.fsabino.banking_business.apimodel.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Message {

    private UUID operationId;
    private String message;

    public UUID getOperationId() {
        return operationId;
    }

    public String getMessage() {
        return message;
    }

    private Message(UUID operationId, String message) {
        this.operationId = operationId;
        this.message = message;
    }

    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    public static class MessageBuilder {

        @JsonProperty("operation_id")
        private UUID operationId;

        @JsonProperty
        private String message;

        public MessageBuilder operationId(UUID operationId) {
            this.operationId = operationId;
            return this;
        }

        public MessageBuilder message(String message) {
            this.message = message;
            return this;
        }

        public Message build() {
            return new Message(this.operationId, this.message);
        }
    }
}
