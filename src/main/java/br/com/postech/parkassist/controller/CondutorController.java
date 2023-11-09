package br.com.postech.parkassist.controller;

import br.com.postech.parkassist.controller.request.CondutorRequest;
import br.com.postech.parkassist.controller.request.VeiculoRequest;
import br.com.postech.parkassist.customexception.CustomException;
import br.com.postech.parkassist.model.Condutor;
import br.com.postech.parkassist.service.CondutorService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;



@Path("/condutor")
public class CondutorController{

	@Inject
	CondutorService condutorservice;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Condutor> listAllCondutor() {
		return condutorservice.listaDeCondutores();
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Condutor buscaCondutorPorID(@PathParam("id") String id) throws Exception{
		return condutorservice.buscaContudorID(id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response create(@Valid  Condutor condutor) throws Exception{
		condutorservice.create(condutor);
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}")
	public Response update(@Valid @PathParam("id") String id, CondutorRequest condutorRequest) throws Exception{
		condutorservice.update(id, condutorRequest);
		return Response.ok().build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) throws CustomException{
		condutorservice.delete(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@POST//PATCH?
	@Path("/{id}/adicionaVeiculo")
	public Response addVeiculo(@PathParam("id") String id, VeiculoRequest veiculoRequest) throws CustomException{
		condutorservice.criarVeiculo(id, veiculoRequest);
		return Response.ok().build();
	}
}