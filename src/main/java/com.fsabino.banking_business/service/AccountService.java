package com.fsabino.banking_business.service;

import com.fsabino.banking_business.apimodel.response.Account;
import com.fsabino.banking_business.converter.AccountDataToAccountConverter;
import com.fsabino.banking_business.exception.AccountNotFoundException;
import com.fsabino.banking_business.exception.ClientForbiddenException;
import com.fsabino.banking_business.repository.AccountRepository;
import com.fsabino.banking_business.repository.data.AccountData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AccountService {

    private Logger log = LoggerFactory.getLogger(AccountService.class);

    private final AccountRepository accountRepository;
    private final ClientService clientService;
    private final AccountDataToAccountConverter accountDataToAccountConverter;

    public AccountService(AccountRepository accountRepository,
                          ClientService clientService,
                          AccountDataToAccountConverter accountDataToAccountConverter) {
        this.accountRepository = accountRepository;
        this.clientService = clientService;
        this.accountDataToAccountConverter = accountDataToAccountConverter;
    }

    public Account getAccount(long clientId, int pin, String accountNumber) throws ClientForbiddenException, AccountNotFoundException {
        log.info("Get Account By AccountNumber {}", accountNumber);
        //TODO(fjsabino022): this method could be an aspect
        this.clientService.isClientAllowed(clientId, pin);
        return this.accountDataToAccountConverter.apply(this.getAccountByClientIdAndAccountNumber(clientId, accountNumber));
    }

    AccountData getAccountByClientIdAndAccountNumber(long clientId, String accountNumber)
            throws AccountNotFoundException {
        log.info("Get Account By ClientId {} And AccountNumber {}", clientId, accountNumber);
        return this.accountRepository.getAccountByClientIdAndAccountNumber(clientId, accountNumber)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account Number %s not found for Client %s", accountNumber, clientId)));
    }

    AccountData getAccountDataByCBU(String cbu) throws AccountNotFoundException {
        log.info("Get Account Data By CBU {}", cbu);
        return this.accountRepository.getAccountByCBU(cbu)
                .orElseThrow(() -> new AccountNotFoundException(String.format("Account not found by cbu %s", cbu)));
    }

    void subtractMoneyToAccount(String cbu, double amount) {
        log.info("Subtract Money To Account cbu {} amount {}", cbu, amount);
        this.accountRepository.subtractMoneyToAccountByCbu(cbu, amount);
    }

    void addMoneyToAccount(String cbu, double amount) {
        log.info("Add Money To Account cbu {} amount {}", cbu, amount);
        this.accountRepository.addMoneyToAccountByCbu(cbu, amount);
    }
}
