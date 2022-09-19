package io.branch.github.commons.exception.mapper;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.lang3.StringUtils;

import io.branch.github.commons.exception.ErrorResponseDTO;
import io.branch.github.commons.exception.GithubAccessForbiddenException;

@Provider
public class GithubAccessForbiddenExceptionMapper implements ExceptionMapper<GithubAccessForbiddenException> {
	@Override
	public Response toResponse(final GithubAccessForbiddenException e) {
		return Response.status(Response.Status.FORBIDDEN)
				.type(MediaType.APPLICATION_JSON)
				.entity(createErrorResponse(e))
				.build();
	}

	private ErrorResponseDTO createErrorResponse(final GithubAccessForbiddenException e) {
		final String notFoundMessage = StringUtils.isNotEmpty(e.getMessage()) ? e.getMessage() : "Access is forbidden";
		ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO();
		errorResponseDTO.setCode(Response.Status.FORBIDDEN.getStatusCode());
		errorResponseDTO.setMessage(notFoundMessage);
		return errorResponseDTO;
	}
}
