package br.com.postech.parkassist.controller;

import br.com.postech.parkassist.controller.request.TempoFixoRequest;
import br.com.postech.parkassist.controller.request.TempoVariavelRequest;
import br.com.postech.parkassist.model.Estacionamento;
import br.com.postech.parkassist.service.EstacionamentoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.List;

@Path("/estacionamento")
public class EstacionamentoController {

    @Inject
    EstacionamentoService estacionamentoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Estacionamento> getAllCondutor() {
        return Estacionamento.listAll();
    }

    @POST
    @Path("/tempofixo")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTempoFixo(TempoFixoRequest tempoFixoRequest) {
        estacionamentoService.iniciarTempoFixo(tempoFixoRequest);
        return Response.ok().build();
    }

    @POST
    @Path("/tempovariavel")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTempoVariavel(TempoVariavelRequest request) {
        estacionamentoService.iniciarTempoVariavel(request);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Estacionamento estacionamento) {
        estacionamentoService.update(id, estacionamento);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        Estacionamento.deleteById(new ObjectId(id));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PATCH
    @Path("/finaliza/{id}")
    public Response finaliza(@PathParam("id") String id) {
        estacionamentoService.finalizaEstacionamento(id);
        return Response.ok().build();
    }
}
