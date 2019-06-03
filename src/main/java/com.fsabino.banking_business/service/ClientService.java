package com.fsabino.banking_business.service;

import com.fsabino.banking_business.exception.ClientForbiddenException;
import com.fsabino.banking_business.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientService {

    private Logger log = LoggerFactory.getLogger(ClientService.class);

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    void isClientAllowed(long clientId, int pin) throws ClientForbiddenException {
        log.info("Is Client Allowed clientId {} and pin {}", clientId, pin);
        if (this.clientRepository.isClientAllowed(clientId, pin)) return;
        throw new ClientForbiddenException(
                String.format("Invalid Client for Id=%s and Pin=%s", clientId, pin));
    }
}
