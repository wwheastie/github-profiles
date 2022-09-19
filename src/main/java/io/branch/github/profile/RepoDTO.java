package io.branch.github.profile;

import java.net.URI;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RepoDTO {
	@JsonProperty("name")
	private String name;

	@JsonProperty("url")
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
