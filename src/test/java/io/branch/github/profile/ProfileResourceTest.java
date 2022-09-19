package io.branch.github.profile;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import java.net.URI;
import java.time.Instant;
import java.util.Collections;

import javax.ws.rs.core.Response;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoSession;
import org.mockito.quality.Strictness;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.branch.github.repo.RepoBO;
import io.branch.github.user.UserBO;

public class ProfileResourceTest {
	private static final String USERNAME = "username";
	private static final Instant NOW = Instant.now();
	private static final URI AVATAR_URL = URI.create("http://avatar.test.io");
	private static final String REPO_NAME = "Repo Name";
	private static final URI REPO_URL = URI.create("http://repo.test.io");

	private final ProfileMapper profileMapper = new ProfileMapper();

	@Mock
	private ProfileBaseService profileBaseService;

	private ProfileResource profileResource;

	private MockitoSession mockito;

	@BeforeMethod
	public void setup() {
		mockito = Mockito.mockitoSession()
				.initMocks(this)
				.strictness(Strictness.STRICT_STUBS)
				.startMocking();

		profileResource = new ProfileResource(profileBaseService, profileMapper);
	}

	@AfterMethod
	public void tearDown() {
		mockito.finishMocking();
	}

	@Test
	public void testGetProfile() {
		when(profileBaseService.getProfileData(USERNAME)).thenReturn(buildUserRepoBO());

		Response response = profileResource.getProfile(USERNAME);
		assertEquals(response.getStatus(), Response.Status.OK.getStatusCode());

		ProfileDTO profileDTO = (ProfileDTO) response.getEntity();

		assertEquals(profileDTO, buildUserRepoDTO());
	}

	private ProfileBO buildUserRepoBO() {
		ProfileBO profileBO = new ProfileBO();
		profileBO.setUser(buildUserBO());
		profileBO.setRepos(Collections.singletonList(buildRepoBO()));
		return profileBO;
	}

	private UserBO buildUserBO() {
		UserBO userBO = new UserBO();
		userBO.setUserName(USERNAME);
		userBO.setCreatedAt(NOW);
		userBO.setAvatar(AVATAR_URL);
		return userBO;
	}

	private RepoBO buildRepoBO() {
		RepoBO repoBO = new RepoBO();
		repoBO.setName(REPO_NAME);
		repoBO.setUrl(REPO_URL);
		return repoBO;
	}

	private ProfileDTO buildUserRepoDTO() {
		ProfileDTO profileDTO = new ProfileDTO();
		profileDTO.setUserName(USERNAME);
		profileDTO.setCreatedAt(NOW);
		profileDTO.setAvatar(AVATAR_URL);
		profileDTO.setRepos(Collections.singletonList(buildRepoDTO()));
		return profileDTO;
	}

	private RepoDTO buildRepoDTO() {
		RepoDTO repoDTO = new RepoDTO();
		repoDTO.setName(REPO_NAME);
		repoDTO.setUrl(REPO_URL);
		return repoDTO;
	}
}
