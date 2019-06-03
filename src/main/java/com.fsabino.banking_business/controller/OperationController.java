package com.fsabino.banking_business.controller;

import com.fsabino.banking_business.apimodel.request.AccountRequest;
import com.fsabino.banking_business.apimodel.request.DepositRequest;
import com.fsabino.banking_business.apimodel.request.TakeOutRequest;
import com.fsabino.banking_business.apimodel.request.TransferRequest;
import com.fsabino.banking_business.apimodel.response.Message;
import com.fsabino.banking_business.service.OperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/operation")
public class OperationController extends AbstractController {

    private Logger log = LoggerFactory.getLogger(OperationController.class);
    private static final String MESSAGE = "Transaction completed succesfully";

    private final OperationService operationService;

    public OperationController(OperationService operationService) {
        this.operationService = operationService;
    }

    @POST
    @Path("/deposit")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response deposit(DepositRequest depositRequest) {
        return post(() -> {
            log.info("Do Deposit with DepositRequest {}", depositRequest);
            return Message.builder()
                    .operationId(this.operationService.doDeposit(depositRequest.getClientId(),
                            depositRequest.getPin(),
                            depositRequest.getAccountNumber(),
                            depositRequest.getAmount()))
                    .message(MESSAGE)
                    .build();
        });
    }

    @POST
    @Path("/takeout")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response takeOut(TakeOutRequest takeOutRequest) {
        return post(() -> {
            log.info("Do Take Out with TakeOutRequest {}", takeOutRequest);
            return Message.builder()
                    .operationId(this.operationService.doTakeOut(takeOutRequest.getClientId(),
                            takeOutRequest.getPin(),
                            takeOutRequest.getAccountNumber(),
                            takeOutRequest.getAmount()))
                    .message(MESSAGE)
                    .build();
        });
    }

    @GET
    @Path("/history")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response getOperations(AccountRequest accountRequest) {
        return get(() -> {
            log.info("Get Operations {}", accountRequest);
            return this.operationService.getOperationsOfAccount(accountRequest.getClientId(),
                    accountRequest.getPin(),
                    accountRequest.getAccountNumber());
        });
    }

    @POST
    @Path("/transfer")
    @Consumes(APPLICATION_JSON)
    @Produces(APPLICATION_JSON)
    public Response transferMoney(TransferRequest transferRequest) {
        return post(() -> {
            log.info("Do transfer money with TransferMoney {}", transferRequest);
            return Message.builder()
                    .operationId(this.operationService.transferMoneyToAccount(transferRequest.getClientId(),
                            transferRequest.getPin(),
                            transferRequest.getAccountNumber(),
                            transferRequest.getAmount(),
                            transferRequest.getCbu()))
                    .message(MESSAGE)
                    .build();
        });
    }
}
