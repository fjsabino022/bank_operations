package com.fsabino.banking_business.repository;

import com.fsabino.banking_business.repository.data.AccountData;
import com.fsabino.banking_business.repository.data.ClientData;
import com.fsabino.banking_business.repository.data.OperationData;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;

import java.util.List;
import java.util.Set;

import static com.google.common.collect.Lists.newArrayList;

class BankDatabase {

    static final Set<ClientData> clients = ImmutableSet.<ClientData>builder()
            .add(ClientData.builder()
                    .clientId(35099240)
                    .fullName("Sabino Franco Jesus")
                    .pin(102457)
                    .type(ClientData.Type.BLACK)
                    .build())
            .add(ClientData.builder()
                    .clientId(33099240)
                    .fullName("Sabino Tomas Santiago")
                    .pin(102255)
                    .type(ClientData.Type.SELECT)
                    .build())
            .add(ClientData.builder()
                    .clientId(40099240)
                    .fullName("Sabino Maria Belen")
                    .pin(984174)
                    .type(ClientData.Type.GOLD)
                    .build())
            .build();


    static final List<AccountData> accounts = ImmutableList.<AccountData>builder()
            .add(AccountData.builder()
                    .accountNumber("347621/1")
                    .cbu("34435556867534324342343647745")
                    .currency(AccountData.Currency.PESO)
                    .clientId(35099240)
                    .price(0.0)
                    .build())
            .add(AccountData.builder()
                    .accountNumber("347622/2")
                    .cbu("31231312321321321321312312313")
                    .currency(AccountData.Currency.DOLAR)
                    .clientId(35099240)
                    .price(0.0)
                    .build())
            .add(AccountData.builder()
                    .accountNumber("1111111/2")
                    .cbu("11111111111111111111111111111")
                    .currency(AccountData.Currency.PESO)
                    .clientId(33099240)
                    .price(0.0)
                    .build())
            .build();

    static final List<OperationData> operations = newArrayList();
}
