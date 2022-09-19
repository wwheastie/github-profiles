package io.branch.github.profile;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import io.branch.github.repo.RepoBO;
import io.branch.github.repo.RepoService;
import io.branch.github.user.UserBO;
import io.branch.github.user.UserService;

@Component
class ProfileBaseService implements ProfileService {
	private final UserService userService;
	private final RepoService repoService;

	public ProfileBaseService(final UserService userService, final RepoService repoService) {
		this.userService = userService;
		this.repoService = repoService;
	}

	@Override
	@Cacheable(cacheNames = "profile", key = "#username")
	public ProfileBO getProfileData(final String username) {
		final UserBO userBO = userService.getUserData(username);
		final List<RepoBO> repoBOList = repoService.getRepoData(username);
		return createUserRepoBO(userBO, repoBOList);
	}

	private ProfileBO createUserRepoBO(final UserBO userBO, final List<RepoBO> repoBOList) {
		ProfileBO profileBO = new ProfileBO();
		profileBO.setUser(userBO);
		profileBO.setRepos(repoBOList);
		return profileBO;
	}
}
