package com.fsabino.banking_business.config;

import com.google.common.collect.ImmutableSet;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.Set;

import static com.fsabino.banking_business.config.BankFactory.getAccountController;
import static com.fsabino.banking_business.config.BankFactory.getOperationController;
import static com.fsabino.banking_business.config.BankFactory.getPingController;

@ApplicationPath("/banking_business")
public class WebConfig extends Application {

    private final Set<Object> singletons;

    public WebConfig() {
        singletons = ImmutableSet.<Object>builder()
                .add(getOperationController())
                .add(getAccountController())
                .add(getPingController())
                .build();
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
