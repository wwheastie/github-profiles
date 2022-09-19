package io.branch.github.user;

import java.net.URI;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUserDTO {
	@JsonProperty("login")
	private String userName;

	@JsonProperty("name")
	private String displayName;

	@JsonProperty("avatar_url")
	private URI avatar;

	@JsonProperty("location")
	private String geoLocation;

	@JsonProperty("email")
	private String email;

	@JsonProperty("url")
	private String url;

	@JsonProperty("created_at")
	private Instant createdAt;

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
}
