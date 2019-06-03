package com.fsabino.banking_business.service;

import com.fsabino.banking_business.apimodel.response.Operation;
import com.fsabino.banking_business.converter.OperationDataToOperationConverter;
import com.fsabino.banking_business.exception.AccountNotFoundException;
import com.fsabino.banking_business.exception.ClientForbiddenException;
import com.fsabino.banking_business.exception.OperationForbiddenException;
import com.fsabino.banking_business.repository.OperationRepository;
import com.fsabino.banking_business.repository.data.AccountData;
import com.fsabino.banking_business.repository.data.DepositData;
import com.fsabino.banking_business.repository.data.TakeOutData;
import com.fsabino.banking_business.repository.data.TransferData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;
import java.util.function.BiFunction;

import static java.util.stream.Collectors.toList;

public class OperationService {

    private Logger log = LoggerFactory.getLogger(OperationService.class);

    private final ClientService clientService;
    private final AccountService accountService;
    private final OperationRepository operationRepository;
    private final OperationDataToOperationConverter operationDataToOperationConverter;

    public OperationService(ClientService clientService,
                            OperationRepository operationRepository,
                            AccountService accountService,
                            OperationDataToOperationConverter operationDataToOperationConverter) {
        this.clientService = clientService;
        this.operationRepository = operationRepository;
        this.accountService = accountService;
        this.operationDataToOperationConverter = operationDataToOperationConverter;
    }

    public UUID doDeposit(long clientId, int pin, String accountNumber, double amount)
            throws AccountNotFoundException, ClientForbiddenException {
        log.info("Do deposit with clientId {}, pin {}, accountNumber {} and amount {}", clientId, pin, accountNumber, amount);
        return this.executeAction(clientId, pin, accountNumber, amount, (account, value) -> {
            log.info("Execute Deposit accountNumber {} amount {}", account.getAccountNumber(), value);
            this.accountService.addMoneyToAccount(account.getCbu(), value);
            log.info("Save operation");
            return this.operationRepository.save(DepositData.builder()
                    .amount(value)
                    .cbu(account.getCbu())
                    .build());
        });
    }

    public UUID doTakeOut(long clientId, int pin, String accountNumber, double amount)
            throws AccountNotFoundException, ClientForbiddenException {
        log.info("Do take out with clientId {}, pin {}, accountNumber {} and amount {}", clientId, pin, accountNumber, amount);
        return this.executeAction(clientId, pin, accountNumber, amount, (account, value) -> {
            log.info("Execute Take Out accountNumber {} amount {}", account.getAccountNumber(), value);
            this.checkIsPossibleToDoOperation(account.getPrice(), value);
            this.accountService.subtractMoneyToAccount(account.getCbu(), value);
            log.info("Save operation");
            return this.operationRepository.save(TakeOutData.builder()
                    .amount(value)
                    .cbu(account.getCbu())
                    .build());
        });
    }

    public List<Operation> getOperationsOfAccount(long clientId, int pin, String accountNumber)
            throws ClientForbiddenException, AccountNotFoundException {
        log.info("Get Operations Of Account with accountNumber {}", accountNumber);
        AccountData accountData = checkClientAllowedAndAccountData(clientId, pin, accountNumber);

        log.info("Get Operations");
        return this.operationRepository.getOperationsDataByCBU(accountData.getCbu())
                .stream()
                .map(this.operationDataToOperationConverter)
                .collect(toList());
    }

    public UUID transferMoneyToAccount(long clientId, int pin, String accountNumber, double amount, String cbuToTransfer)
            throws ClientForbiddenException, AccountNotFoundException {
        log.info("Transfer Money To Account cbuToTransfer {}", cbuToTransfer);
        AccountData accountData = checkClientAllowedAndAccountData(clientId, pin, accountNumber);

        this.checkIsPossibleToDoOperation(accountData.getPrice(), amount);
        AccountData accountDataToTransfer = this.accountService.getAccountDataByCBU(cbuToTransfer);

        this.accountService.subtractMoneyToAccount(accountData.getCbu(), amount);
        this.accountService.addMoneyToAccount(accountDataToTransfer.getCbu(), amount);

        log.info("Save operation");
        return this.operationRepository.save(TransferData.builder()
                .amount(amount)
                .cbu(accountData.getCbu())
                .cbuAnotherAccount(accountDataToTransfer.getCbu())
                .build());
    }

    private AccountData checkClientAllowedAndAccountData(long clientId, int pin, String accountNumber)
            throws AccountNotFoundException, ClientForbiddenException {
        //TODO(fjsabino022): this method could be an aspect
        this.clientService.isClientAllowed(clientId, pin);
        return this.accountService.getAccountByClientIdAndAccountNumber(clientId, accountNumber);
    }

    private UUID executeAction(long clientId, int pin, String accountNumber, double amount,
                               BiFunction<AccountData, Double, UUID> biFunction)
            throws AccountNotFoundException, ClientForbiddenException {
        AccountData accountData = checkClientAllowedAndAccountData(clientId, pin, accountNumber);
        return biFunction.apply(accountData, amount);
    }

    private void checkIsPossibleToDoOperation(double currentMoney, double amount) {
        log.info("Check if is possible operation currentMoney {} and amountToTakeOut {}", currentMoney, amount);
        if (amount > currentMoney) {
            log.info("Amount is major than current money. Current money {}", currentMoney);
            throw new OperationForbiddenException(String.format("Amount is major than current money. " +
                    "Amount %s Current Money %s", amount, currentMoney));
        }
    }
}
