package br.com.postech.parkassist.controller;

import br.com.postech.parkassist.model.Estacionamento;
import br.com.postech.parkassist.service.EstacionamentoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.UUID;

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
    @Path("/tempofixo/{idCondutor}/{tempo}")
    //@Consumes(MediaType.APPLICATION_JSON)
    public Response createTempoFixo(@PathParam("idCondutor") String idCondutor, @PathParam("tempo") Integer tempo) {
        System.out.println("teste");
        ObjectId id = new ObjectId(idCondutor);
//        UUID id = new UUID(idContudor);
        estacionamentoService.iniciarTempoFixo(id, tempo);
        return Response.ok().build();
    }

    @POST
    @Path("/tempovariavel/{idCondutor}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createTempoVariavel(@PathParam("idCondutor") String idCondutor) {
        ObjectId id = new ObjectId(idCondutor);
        estacionamentoService.iniciarTempoVariavel(id);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Estacionamento estacionamento) {
        estacionamento.setId(new ObjectId(id));
        estacionamento.update();
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
