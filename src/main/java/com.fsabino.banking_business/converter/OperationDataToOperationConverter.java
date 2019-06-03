package com.fsabino.banking_business.converter;

import com.fsabino.banking_business.apimodel.response.Operation;
import com.fsabino.banking_business.repository.data.OperationData;

import java.time.OffsetDateTime;
import java.util.function.Function;

import static java.time.format.DateTimeFormatter.ISO_OFFSET_DATE;

public class OperationDataToOperationConverter implements Function<OperationData, Operation> {

    @Override
    public Operation apply(OperationData operationData) {
        Operation.OperationBuilder operationBuilder = Operation.builder()
                .amount(operationData.getAmount())
                .cbu(operationData.getCbu())
                .operationId(operationData.getOperationId());

        OperationData.Type type = operationData.getType();
        if (type != null) {
            operationBuilder.type(type.name());
        } else {
            throw new IllegalArgumentException("type cannot be null");
        }

        OffsetDateTime dateTime = operationData.getDatetime();
        if (dateTime != null) {
            operationBuilder.dateTime(dateTime.format(ISO_OFFSET_DATE));
        } else {
            throw new IllegalArgumentException("dateTime cannot be null");
        }

        return operationBuilder.build();
    }
}
