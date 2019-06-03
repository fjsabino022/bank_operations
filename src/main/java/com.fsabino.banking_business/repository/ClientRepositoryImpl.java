package com.fsabino.banking_business.repository;

import com.fsabino.banking_business.repository.data.ClientData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class ClientRepositoryImpl implements ClientRepository {

    private Logger log = LoggerFactory.getLogger(ClientRepositoryImpl.class);

    @Override
    public boolean isClientAllowed(long clientId, int pin) {
        log.info("Is Client Allowed clientId {} pin {}", clientId, pin);
        return BankDatabase.clients.stream()
                .anyMatch(clientData -> clientData.getClientId() == clientId &&
                        clientData.getPin() == pin);
    }

    @Override
    public Optional<ClientData> getClientById(long clientId) {
        log.info("Get Client By clientId {}", clientId);
        return BankDatabase.clients.stream()
                .filter(clientData -> clientData.getClientId() == clientId)
                .findFirst();
    }
}
