package io.branch.github.profile;

import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import io.branch.github.repo.RepoBO;
import io.branch.github.user.UserBO;

public class ProfileBO {
	private UserBO user;
	private List<RepoBO> repos;

	public UserBO getUser() {
		return user;
	}

	public void setUser(final UserBO user) {
		this.user = user;
	}

	public List<RepoBO> getRepos() {
		return repos;
	}

	public void setRepos(final List<RepoBO> repos) {
		this.repos = repos;
	}

	@Override
	public boolean equals(final Object o) {
		return EqualsBuilder.reflectionEquals(this, o, false);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}
}
