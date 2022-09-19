package io.branch.github.repo;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

@Component
class RepoMapper extends ConfigurableMapper {
	@Override
	protected void configure(final MapperFactory mapperFactory) {
		mapperFactory.classMap(GithubRepoDTO.class, RepoBO.class)
				.byDefault()
				.register();
	}
}
