package io.branch.github.config;

import org.glassfish.jersey.jackson.internal.jackson.jaxrs.json.JacksonJsonProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import io.branch.github.PingResource;
import io.branch.github.commons.exception.mapper.FallbackExceptionMapper;
import io.branch.github.commons.exception.mapper.GithubAccessForbiddenExceptionMapper;
import io.branch.github.commons.exception.mapper.GithubUserNotFoundExceptionMapper;
import io.branch.github.profile.ProfileResource;

@Configuration
public class JerseyConfig extends ResourceConfig {
	public JerseyConfig() {
		register(JacksonJsonProvider.class);
		register(ProfileResource.class);
		register(PingResource.class);
		register(FallbackExceptionMapper.class);
		register(GithubAccessForbiddenExceptionMapper.class);
		register(GithubUserNotFoundExceptionMapper.class);
	}
}
