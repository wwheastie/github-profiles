package io.branch.github.commons.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;

import io.branch.github.commons.exception.ErrorResponseDTO;
import io.branch.github.commons.exception.GithubUserNotFoundException;

@Provider
public class GithubUserNotFoundExceptionMapper implements ExceptionMapper<GithubUserNotFoundException> {
	@Override
	public Response toResponse(final GithubUserNotFoundException e) {
		return Response.status(Response.Status.NOT_FOUND)
				.type(MediaType.APPLICATION_JSON)
				.entity(createErrorResponse(e))
				.build();
	}

	private ErrorResponseDTO createErrorResponse(final GithubUserNotFoundException e) {
		final String notFoundMessage = StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : "Could not find requested entity";
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setCode(Response.Status.NOT_FOUND.getStatusCode());
		errorResponseDTO.setMessage(notFoundMessage);
		return errorResponseDTO;
	}
}
