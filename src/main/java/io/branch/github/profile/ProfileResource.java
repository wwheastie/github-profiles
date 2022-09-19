package io.branch.github.profile;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.BoundMapperFacade;

@Component
@Path("/api/v1/username/{username}")
public class ProfileResource {
	private final ProfileBaseService userRepoService;
	private final BoundMapperFacade<ProfileBO, ProfileDTO> userRepoBOToDTOMapper;

	public ProfileResource(final ProfileBaseService userRepoService, final ProfileMapper profileMapper) {
		this.userRepoService = userRepoService;
		userRepoBOToDTOMapper = profileMapper.dedicatedMapperFor(ProfileBO.class, ProfileDTO.class);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProfile(@PathParam("username") final String username) {
		final ProfileBO profileBO = userRepoService.getProfileData(username);
		final ProfileDTO profileDTO = userRepoBOToDTOMapper.map(profileBO);
		return Response.ok()
				.entity(profileDTO)
				.build();
	}
}
