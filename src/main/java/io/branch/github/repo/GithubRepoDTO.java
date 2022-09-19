package io.branch.github.repo;

import java.net.URI;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubRepoDTO {
	@JsonProperty("name")
	private String name;

	@JsonProperty("html_url")
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
}
