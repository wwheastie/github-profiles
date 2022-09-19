package io.branch.github.repo;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.quality.Strictness;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.branch.github.commons.client.GithubClient;

public class RepoBaseServiceTest {
	private static final String USERNAME = "username";
	private static final URI TEST_URI = URI.create("https://api.github.com/users/" + USERNAME + "/repos");
	private static final URI REPO_URI = URI.create("http://test-repo.io");
	private static final String REPO_NAME = "Repo Name";

	private final RepoMapper repoMapper = new RepoMapper();

	@Mock
	private GithubClient githubClient;

	@Mock
	private Response response;

	private RepoBaseService service;
	private MockitoSession mockito;

	@BeforeMethod
	public void setup() {
		mockito = Mockito.mockitoSession()
				.initMocks(this)
				.strictness(Strictness.STRICT_STUBS)
				.startMocking();
		service = new RepoBaseService(githubClient, repoMapper);
	}

	@AfterMethod
	public void tearDown() {
		mockito.finishMocking();
	}

	@Test
	public void testGetRepoData() {
		when(response.readEntity(any(GenericType.class))).thenReturn(buildGithubRepoDTO());
		when(githubClient.getResponse(TEST_URI)).thenReturn(response);

		List<RepoBO> repoBOList = service.getRepoData(USERNAME);

		RepoBO repoBO = repoBOList.get(0);
		assertEquals(repoBO.getName(), REPO_NAME);
		assertEquals(repoBO.getUrl(), REPO_URI);
	}

	private List<GithubRepoDTO> buildGithubRepoDTO() {
		GithubRepoDTO githubRepoDTO = new GithubRepoDTO();
		githubRepoDTO.setUrl(REPO_URI);
		githubRepoDTO.setName(REPO_NAME);

		return Collections.singletonList(githubRepoDTO);
	}
}
