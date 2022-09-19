package io.branch.github.profile;

import java.net.URI;
import java.time.Instant;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProfileDTO {
	@JsonProperty("user_name")
	private String userName;

	@JsonProperty("display_name")
	private String displayName;

	@JsonProperty("avatar")
	private URI avatar;

	@JsonProperty("geo_location")
	private String geoLocation;

	@JsonProperty("email")
	private String email;

	@JsonProperty("url")
	private String url;

	@JsonProperty("created_at")
	private Instant createdAt;

	@JsonProperty("repos")
	private List<RepoDTO> repos;

	public String getUserName() {
		return userName;
	}

	public void setUserName(final String userName) {
		this.userName = userName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(final String displayName) {
		this.displayName = displayName;
	}

	public URI getAvatar() {
		return avatar;
	}

	public void setAvatar(final URI avatar) {
		this.avatar = avatar;
	}

	public String getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(final String geoLocation) {
		this.geoLocation = geoLocation;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(final String url) {
		this.url = url;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(final Instant createdAt) {
		this.createdAt = createdAt;
	}

	public List<RepoDTO> getRepos() {
		return repos;
	}

	public void setRepos(final List<RepoDTO> repos) {
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
