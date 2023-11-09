package br.com.postech.parkassist.customexception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class CustomExceptionHandler implements ExceptionMapper<CustomException>{

	String notFound;

    @Override
    public Response toResponse(CustomException e) {

        if (e.getMessage().equalsIgnoreCase(notFound)) {
            return Response.status(Response.Status.NOT_FOUND).
                   entity(new ErrorMessage("Constraint Violation", e.getMessage(), Response.status(Response.Status.BAD_REQUEST).build().getStatus()))
          .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).
               entity(new ErrorMessage("Constraint Violation", e.getMessage(), Response.status(Response.Status.BAD_REQUEST).build().getStatus() ))
          .build();
        }
    }
	
	
	
}
