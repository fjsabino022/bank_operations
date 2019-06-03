package com.fsabino.banking_business.repository;

import com.fsabino.banking_business.repository.data.OperationData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class OperationRepositoryImpl implements OperationRepository {

    private Logger log = LoggerFactory.getLogger(OperationRepositoryImpl.class);

    @Override
    public UUID save(OperationData operation) {
        log.info("Save OperationData {}", operation);
        BankDatabase.operations.add(operation);
        return operation.getOperationId();
    }

    @Override
    public List<OperationData> getOperationsDataByCBU(String cbu) {
        log.info("Get Operations By cbu {}", cbu);
        return BankDatabase.operations.stream()
                .filter(operationData -> isNotBlank(operationData.getCbu()))
                .filter(operationData -> operationData.getCbu().equals(cbu))
                .collect(toList());
    }
}
