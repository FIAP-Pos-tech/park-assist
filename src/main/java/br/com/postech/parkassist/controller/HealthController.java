package br.com.postech.parkassist.controller;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;

@Path("/health")
public class HealthController {
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String health() {
        return LocalDateTime.now().toString();
    }
}
