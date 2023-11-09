package br.com.postech.parkassist.exceptions;

import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.ext.ExceptionMapper;

public class CondutorExceptionMapper implements ExceptionMapper<BadRequestException> {

	 @Override
	 public Response toResponse(BadRequestException badReqException) {
		    CondutorExcecao nossaExcecao = new CondutorExcecao();
	        nossaExcecao.setMensagem(badReqException.getMessage());
	        return Response.status(Status.BAD_REQUEST).entity(nossaExcecao).build();
	    }
	
	
	
	
}
