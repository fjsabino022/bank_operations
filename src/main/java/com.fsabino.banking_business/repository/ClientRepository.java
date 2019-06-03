package com.fsabino.banking_business.repository;

import com.fsabino.banking_business.repository.data.ClientData;

import java.util.Optional;

public interface ClientRepository {

    boolean isClientAllowed(long clientId, int pin);

    Optional<ClientData> getClientById(long clientId);
}
