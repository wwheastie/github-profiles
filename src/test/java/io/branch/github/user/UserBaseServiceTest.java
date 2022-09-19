package io.branch.github.user;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.time.Instant;

import javax.ws.rs.core.Response;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.quality.Strictness;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.branch.github.commons.client.GithubClient;
import io.branch.github.commons.exception.GithubAccessForbiddenException;
import io.branch.github.commons.exception.GithubUserNotFoundException;

public class UserBaseServiceTest {
	private static final String USERNAME = "username";
	private static final Instant NOW = Instant.now();
	private static final URI AVATAR_URL = URI.create("http://avatar.test.io");
	private static final URI TEST_URI = URI.create("https://api.github.com/users/" + USERNAME);

	private final UserMapper repoMapper = new UserMapper();

	@Mock
	private GithubClient githubClient;

	@Mock
	private Response response;

	private UserBaseService service;
	private MockitoSession mockito;

	@BeforeMethod
	public void setup() {
		mockito = Mockito.mockitoSession()
				.initMocks(this)
				.strictness(Strictness.STRICT_STUBS)
				.startMocking();
		service = new UserBaseService(githubClient, repoMapper);
	}

	@AfterMethod
	public void tearDown() {
		mockito.finishMocking();
	}

	@Test
	public void testGetRepoData() {
		when(response.getStatus()).thenReturn(Response.Status.OK.getStatusCode());
		when(response.readEntity(GithubUserDTO.class)).thenReturn(buildGithubUserDTO());
		when(githubClient.getResponse(TEST_URI)).thenReturn(response);

		UserBO userBO = service.getUserData(USERNAME);

		assertEquals(userBO.getUserName(), USERNAME);
		assertEquals(userBO.getAvatar(), AVATAR_URL);
		assertEquals(userBO.getCreatedAt(), NOW);
	}

	@Test(expectedExceptions = GithubUserNotFoundException.class)
	public void testGetNonExistentUserData() {
		when(githubClient.getResponse(TEST_URI)).thenReturn(Response.status(Response.Status.NOT_FOUND).build());
		service.getUserData(USERNAME);
	}

	@Test(expectedExceptions = GithubAccessForbiddenException.class)
	public void testGetForbidden() {
		when(githubClient.getResponse(TEST_URI)).thenReturn(Response.status(Response.Status.FORBIDDEN).build());
		service.getUserData(USERNAME);
	}

	private GithubUserDTO buildGithubUserDTO() {
		GithubUserDTO githubUserDTO = new GithubUserDTO();
		githubUserDTO.setUserName(USERNAME);
		githubUserDTO.setCreatedAt(NOW);
		githubUserDTO.setAvatar(AVATAR_URL);
		return githubUserDTO;
	}
}
