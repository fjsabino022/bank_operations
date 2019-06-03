package com.fsabino.banking_business.converter;

import com.fsabino.banking_business.apimodel.response.Account;
import com.fsabino.banking_business.repository.data.AccountData;

import java.util.function.Function;

public class AccountDataToAccountConverter implements Function<AccountData, Account> {

    @Override
    public Account apply(AccountData accountData) {
        return Account.builder()
                .accountNumber(accountData.getAccountNumber())
                .cbu(accountData.getCbu())
                .clientId(accountData.getClientId())
                .currency(accountData.getCurrency().getName())
                .price(accountData.getPrice())
                .build();
    }
}
