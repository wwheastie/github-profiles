package io.branch.github.repo;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import io.branch.github.commons.client.GithubClient;

@Component
class RepoBaseService implements RepoService {
	private static final String GITHUB_API_BASE_URL = "https://api.github.com";
	private static final String GITHUB_REPO_PATH = "/users/{username}/repos";
	private static final String USERNAME_TEMPLATE = "username";

	private final GithubClient githubClient;
	private final RepoMapper repoMapper;

	public RepoBaseService(final GithubClient githubClient, final RepoMapper repoMapper) {
		this.githubClient = githubClient;
		this.repoMapper = repoMapper;
	}

	@Override
	@Cacheable(cacheNames = "repo", key = "#username")
	public List<RepoBO> getRepoData(final String username) {
		URI githubRepoUri = buildGithubRepoUri(username);
		Response response = githubClient.getResponse(githubRepoUri);
		List<GithubRepoDTO> githubRepoDTOList = response.readEntity(new GenericType<>() {});

		if (CollectionUtils.isEmpty(githubRepoDTOList)) {
			return Collections.emptyList();
		}

		return repoMapper.mapAsList(githubRepoDTOList, RepoBO.class);
	}

	private URI buildGithubRepoUri(final String username) {
		return UriBuilder.fromUri(GITHUB_API_BASE_URL)
				.path(GITHUB_REPO_PATH)
				.resolveTemplate(USERNAME_TEMPLATE, username)
				.build();
	}
}
