package com.fsabino.banking_business.repository;

import com.fsabino.banking_business.repository.data.AccountData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Consumer;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

public class AccountRepositoryImpl implements AccountRepository {

    private Logger log = LoggerFactory.getLogger(AccountRepositoryImpl.class);

    @Override
    public Optional<AccountData> getAccountByClientIdAndAccountNumber(long clientId, String accountNumber) {
        log.info("Get Account By ClientId {} And AccountNumber {}", clientId, accountNumber);
        Optional<AccountData> account = BankDatabase.accounts.stream()
                .filter(a -> a.getClientId() == clientId &&
                        a.getAccountNumber().equals(accountNumber))
                .findAny();

        log.info("AccountData {}", account);
        return account;
    }

    @Override
    public void addMoneyToAccountByCbu(String cbu, double amount) {
        log.info("Add Money To Account By Cbu {} amount {}", cbu, amount);
        this.executeActionToAccount(cbu, a -> a.addMoney(amount));
    }

    @Override
    public void subtractMoneyToAccountByCbu(String cbu, double amount) {
        log.info("Subtract Money To Account By Cbu {} amount {}", cbu, amount);
        this.executeActionToAccount(cbu, a -> a.subtractMoney(amount));
    }

    @Override
    public Optional<AccountData> getAccountByCBU(String cbu) {
        log.info("Get Account By CBU", cbu);
        Optional<AccountData> account = BankDatabase.accounts.stream()
                .filter(a -> isNotBlank(a.getCbu()))
                .filter(a -> a.getCbu().equals(cbu))
                .findAny();

        log.info("AccountData {}", account);
        return account;
    }

    private void executeActionToAccount(String cbu, Consumer<AccountData> consumer) {
        BankDatabase.accounts.stream()
                .filter(a -> a.getCbu().equals(cbu))
                .findAny()
                .ifPresent(consumer);
    }
}
