package io.branch.github.commons.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;

import io.branch.github.commons.exception.ErrorResponseDTO;

@Provider
public class FallbackExceptionMapper implements ExceptionMapper<Exception> {
	@Override
	public Response toResponse(final Exception e) {
		return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.type(MediaType.APPLICATION_JSON)
				.entity(createErrorResponse(e))
				.build();
	}

	private ErrorResponseDTO createErrorResponse(final Exception e) {
		final String notFoundMessage = StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : "Internal error occurred";
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setCode(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
		errorResponseDTO.setMessage(notFoundMessage);
		return errorResponseDTO;
	}
}
