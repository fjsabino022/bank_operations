package com.fsabino.banking_business.repository;

import com.fsabino.banking_business.repository.data.OperationData;

import java.util.List;
import java.util.UUID;

public interface OperationRepository {

    UUID save(OperationData operation);

    List<OperationData> getOperationsDataByCBU(String cbu);
}
