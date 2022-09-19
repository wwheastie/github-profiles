package io.branch.github.repo;

import java.util.List;

public interface RepoService {
	List<RepoBO> getRepoData(String username);
}
