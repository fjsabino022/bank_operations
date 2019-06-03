package com.fsabino.banking_business.controller;

import com.fsabino.banking_business.apimodel.response.Message;
import com.fsabino.banking_business.exception.AccountNotFoundException;
import com.fsabino.banking_business.exception.ClientForbiddenException;
import org.apache.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.concurrent.Callable;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;

abstract class AbstractController {

    private Logger log = LoggerFactory.getLogger(AbstractController.class);

    <T> Response get(Callable<T> callable) {

        return executeGet(callable);
    }

    <T> Response post(Callable<T> callable) {

        return executePost(callable);
    }

    private <T> Response executeGet(Callable<T> callable) {
        Response response;
        try {
            response = Response.ok()
                    .entity(callable.call())
                    .build();
        } catch (ClientForbiddenException e) {
            log.error("ClientForbiddenException", e);
            response = Response.status(SC_FORBIDDEN)
                    .entity(Message.builder().message(e.getMessage()).build())
                    .build();
        } catch (AccountNotFoundException e) {
            log.error("AccountNotFoundException", e);
            response = Response.status(SC_NOT_FOUND)
                    .entity(Message.builder().message(e.getMessage()).build())
                    .build();
        } catch (Exception e) {
            log.error("Exception", e);
            response = Response.serverError()
                    .entity(Message.builder().message(e.getMessage()).build())
                    .build();
        }
        return response;
    }

    private <T> Response executePost(Callable<T> callable) {
        Response response;
        try {
            response = Response.status(SC_CREATED)
                    .entity(callable.call())
                    .build();
        } catch (ClientForbiddenException e) {
            log.error("ClientForbiddenException", e);
            response = Response.status(SC_FORBIDDEN)
                    .entity(Message.builder().message(e.getMessage()).build())
                    .build();
        } catch (AccountNotFoundException e) {
            log.error("AccountNotFoundException", e);
            response = Response.status(SC_NOT_FOUND)
                    .entity(Message.builder().message(e.getMessage()).build())
                    .build();
        } catch (Exception e) {
            log.error("Exception", e);
            response = Response.serverError()
                    .entity(Message.builder().message(e.getMessage()).build())
                    .build();
        }
        return response;
    }
}

