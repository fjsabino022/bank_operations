package com.fsabino.banking_business.controller;

import com.fsabino.banking_business.apimodel.request.AccountRequest;
import com.fsabino.banking_business.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/account")
public class AccountController extends AbstractController {

    private Logger log = LoggerFactory.getLogger(AccountController.class);

    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GET
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getAccount(AccountRequest accountRequest) {
        return get(() -> {
            log.info("GetAccount {}", accountRequest);
            return this.accountService.getAccount(accountRequest.getClientId(),
                    accountRequest.getPin(),
                    accountRequest.getAccountNumber());
        });
    }
}
