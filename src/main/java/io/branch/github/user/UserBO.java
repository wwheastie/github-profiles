package io.branch.github.user;

import java.net.URI;
import java.time.Instant;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class UserBO {
	private String userName;
	private String displayName;
	private URI avatar;
	private String geoLocation;
	private String email;
	private String url;
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

	@Override
	public boolean equals(final Object o) {
		return EqualsBuilder.reflectionEquals(this, o, false);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this, false);
	}
}
