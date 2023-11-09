package br.com.postech.parkassist.controller;

import br.com.postech.parkassist.controller.request.TempoFixoRequest;
import br.com.postech.parkassist.controller.request.TempoVariavelRequest;
import br.com.postech.parkassist.customexception.CustomException;
import br.com.postech.parkassist.model.Estacionamento;
import br.com.postech.parkassist.service.EstacionamentoService;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/estacionamento")
public class EstacionamentoController {

	@Inject
	EstacionamentoService estacionamentoService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Estacionamento> getAllCondutor() throws CustomException{
		return estacionamentoService.listaDeEstaconamentos();
	}

	@GET
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Estacionamento buscaCondutorPorID(@PathParam("id") String id) throws Exception{
		return estacionamentoService.buscaContudorID(id);
	}
	
	/**
	 * Adiciona Estacionamento por tempo fixo
	 * @param tempoFixoRequest
	 * @return
	 */
	@POST
	@Path("/tempofixo")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTempoFixo(TempoFixoRequest tempoFixoRequest) throws CustomException {
		estacionamentoService.iniciarTempoFixo(tempoFixoRequest);
		return Response.ok().build();
	}

	/**
	 * Adiciona Estacionamento por tempo vareavel
	 * @param tempoFixoRequest
	 * @return
	 */
	@POST
	@Path("/tempovariavel")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createTempoVariavel(TempoVariavelRequest request) throws CustomException{
		estacionamentoService.iniciarTempoVariavel(request);
		return Response.ok().build();
	}

	@PUT
	@Path("/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@Valid @PathParam("id") String id, Estacionamento estacionamento) throws CustomException {
		estacionamentoService.update(id, estacionamento);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@DELETE
	@Path("/{id}")
	public Response delete(@PathParam("id") String id) throws CustomException {
		estacionamentoService.delete(id);
		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@PATCH
	@Path("/finaliza/{id}")
	public Response finaliza(@PathParam("id") String id) throws CustomException{
		estacionamentoService.finalizaEstacionamento(id);
		return Response.ok().build();
	}
}
