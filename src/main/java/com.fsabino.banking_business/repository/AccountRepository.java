package com.fsabino.banking_business.repository;

import com.fsabino.banking_business.repository.data.AccountData;

import java.util.Optional;

public interface AccountRepository {

    Optional<AccountData> getAccountByClientIdAndAccountNumber(long clientId, String accountNumber);

    void addMoneyToAccountByCbu(String cbu, double amount);

    void subtractMoneyToAccountByCbu(String cbu, double amount);

    Optional<AccountData> getAccountByCBU(String cbu);
}