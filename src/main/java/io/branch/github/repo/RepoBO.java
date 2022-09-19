package io.branch.github.repo;

import java.net.URI;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class RepoBO {
	private String name;
	private URI url;

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public URI getUrl() {
		return url;
	}

	public void setUrl(final URI url) {
		this.url = url;
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
