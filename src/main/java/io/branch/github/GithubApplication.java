package io.branch.github;

import java.util.concurrent.TimeUnit;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableCaching
public class GithubApplication {
	public static void main(final String[] args) {
		new SpringApplicationBuilder(GithubApplication.class)
				.bannerMode(Banner.Mode.OFF)
				.logStartupInfo(true)
				.build()
				.run(args);
	}

	@Bean
	public Client getDefaultClient() {
		return ClientBuilder.newBuilder()
				.connectTimeout(1, TimeUnit.SECONDS)
				.readTimeout(2, TimeUnit.SECONDS)
				.build();
	}
}
