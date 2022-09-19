package io.branch.github.commons.client;

import java.net.URI;

import javax.ws.rs.core.Response;

public interface GithubClient {
	Response getResponse(URI url);
}
