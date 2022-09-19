package io.branch.github.commons.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;

@Component
class GithubBaseClient implements GithubClient {
	private final Client client;

	public GithubBaseClient(final Client client) {
		this.client = client;
	}

	@Override
	public Response getResponse(final URI url) {
		return client.target(url)
				.request()
				.header("Authorization", "Bearer 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")
				.get();

	}
}
