package br.com.postech.parkassist.controller;

import br.com.postech.parkassist.model.Condutor;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.bson.types.ObjectId;

import java.util.List;

@Path("/condutor")
public class CondutorController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Condutor> getAllCondutor() {
        return Condutor.listAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response create(Condutor condutor) {
        condutor.persist();
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") String id, Condutor condutor) {
        condutor.setId(new ObjectId(id));
        condutor.update();
        return Response.status(Response.Status.NO_CONTENT).build();
    }


    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") String id) {
        Condutor.deleteById(new ObjectId(id));
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
