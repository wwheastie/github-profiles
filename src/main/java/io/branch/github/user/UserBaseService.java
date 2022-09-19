package io.branch.github.user;

import java.net.URI;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import io.branch.github.commons.client.GithubClient;
import io.branch.github.commons.exception.GithubAccessForbiddenException;
import io.branch.github.commons.exception.GithubUserNotFoundException;
import ma.glasnost.orika.BoundMapperFacade;

@Component
class UserBaseService implements UserService {
	private static final String GITHUB_API_BASE_URL = "https://api.github.com";
	private static final String GITHUB_USER_PATH = "/users/{username}";
	private static final String USERNAME_TEMPLATE = "username";

	private final GithubClient githubClient;
	private final BoundMapperFacade<GithubUserDTO, UserBO> userDTOToBOMapper;

	public UserBaseService(final GithubClient githubClient, final UserMapper userMapper) {
		this.githubClient = githubClient;
		userDTOToBOMapper = userMapper.dedicatedMapperFor(GithubUserDTO.class, UserBO.class);
	}

	@Override
	@Cacheable(cacheNames = "user", key = "#username")
	public UserBO getUserData(final String username) {
		URI githubRepoUri = buildGithubUserUri(username);
		Response response = githubClient.getResponse(githubRepoUri);
		validateResponseCode(response);
		GithubUserDTO githubUserDTO = response.readEntity(GithubUserDTO.class);
		return userDTOToBOMapper.map(githubUserDTO);
	}

	private URI buildGithubUserUri(final String username) {
		return UriBuilder.fromUri(GITHUB_API_BASE_URL)
				.path(GITHUB_USER_PATH)
				.resolveTemplate(USERNAME_TEMPLATE, username)
				.build();
	}

	private void validateResponseCode(final Response response) {
		if (response.getStatus() == Response.Status.NOT_FOUND.getStatusCode()) {
			throw new GithubUserNotFoundException();
		}

		if (response.getStatus() == Response.Status.FORBIDDEN.getStatusCode()) {
			throw new GithubAccessForbiddenException();
		}
	}
}
