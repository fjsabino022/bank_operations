package com.fsabino.banking_business.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/ping")
public class PingController {

    private Logger log = LoggerFactory.getLogger(PingController.class);

    @GET
    public Response ping() {
        log.info("Ping execute");
        return Response.ok().build();
    }
}
